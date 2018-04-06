package rs.ac.uns.ftn.informatika.jpa.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.domain.Actor;
import rs.ac.uns.ftn.informatika.jpa.domain.Projection;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDTO;
import rs.ac.uns.ftn.informatika.jpa.repository.ActorRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ProjectionRepository;

@Service
public class ProjectionServiceImpl implements ProjectionService {

	@Autowired
	private ProjectionRepository projectionRepository;
	@Autowired
	private ActorRepository actorRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	@Override
	public Projection addNewProjection(Projection p) {
		Set<Actor> actors = p.getActors();
		p.setActors(null);
		
		//sacuvaj cist projection
		Projection saved_proj = this.projectionRepository.save(p);
		
		//sacuvaj sve (samo nove) glumce
		for (Actor a : actors) {
			if (actorRepository.findOne(a.getName()) == null)
				actorRepository.save(a);
		}
		
		//dodaj veze u projection_actors
		for (Actor a : actors) {
			final String sql = "insert into projection_actors(projection_id, actors_name) values(?,?)";
	        jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(sql);
	                ps.setLong(1, saved_proj.getId());
	                ps.setString(2, a.getName());
	                return ps;
	            }
	        });
		}

		return saved_proj;
		
	}

	@Override
	public Page<ProjectionDTO> getAllProjections(Pageable pageable) {
		return projectionRepository.findAll(pageable);
	}

	@Override
	public boolean setPosterImage(Long projection_id, byte[] image) {
		int ret = projectionRepository.setPosterImage(
				projection_id,
				new String(Base64.getEncoder().encode(image)));
		return ret != 0;
	}

}
