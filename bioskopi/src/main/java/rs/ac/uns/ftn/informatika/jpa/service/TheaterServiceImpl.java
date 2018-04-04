package rs.ac.uns.ftn.informatika.jpa.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import rs.ac.uns.ftn.informatika.jpa.domain.Hall;
import rs.ac.uns.ftn.informatika.jpa.domain.ProjectionDate;
import rs.ac.uns.ftn.informatika.jpa.domain.Theater;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDTO;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDateDTO;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.TheaterDTO;
import rs.ac.uns.ftn.informatika.jpa.repository.HallRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.TheaterRepository;

@Service
public class TheaterServiceImpl implements TheaterService {

	
	@Autowired
	private TheaterRepository theaterRepository;
	@Autowired
	private HallRepository hallRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	@Override
	public Page<TheaterDTO> getMovieTheaters(String name, Pageable pageable) {
		Assert.notNull(name, "Name ne sme biti null");
		return this.theaterRepository.getMovieThearersByName(name, pageable);
	}

	@Override
	public Page<TheaterDTO> getAllMovieTheaters(Pageable pageable) {
		return this.theaterRepository.getAllMovieThearers(pageable);
	}
	

	@Override
	public Page<TheaterDTO> getPlayTheaters(String name, Pageable pageable) {
		Assert.notNull(name, "Name ne sme biti null");
		return this.theaterRepository.getPlayThearersByName(name, pageable);
	}

	@Override
	public Page<TheaterDTO> getAllPlayTheaters(Pageable pageable) {
		return this.theaterRepository.getAllPlayThearers(pageable);
	}

	@Override
	public TheaterDTO getTheaterById(Long id) {
		return this.theaterRepository.getTheaterById(id);
	}
	
	@Override
	public boolean updateTheater(Long id, Theater t) {
		int ret = this.theaterRepository.updateTheaterById(id, t.getName(), t.getAddress(), t.getDescription(), t.getTheaterType());
		return ret != 0;
	}
	
	@Override
	public Page<Date> getProjectionDatesForTheater(Long id, Pageable pageable) {
		return this.theaterRepository.getAllProjectionDatesForTheater(id, pageable);
	}

	@Override
	public Page<ProjectionDTO> getProjectionsForTheaterForDate(Long id, Date date, Pageable pageable) {
		return this.theaterRepository.getAllProjectionsForTheaterForDate(id, date, pageable);
	}

	@Override
	public Page<ProjectionDateDTO> getProjectionDatesForTheaterForProjection(Long theaterId, Long projectionId, Pageable pageable) {
		return this.theaterRepository.getAllProjectionDatesForTheaterForProjection(theaterId, projectionId, pageable);
	}

	@Override
	public Page<ProjectionDTO> getProjectionsForTheater(Long id, Pageable pageable) {
		return this.theaterRepository.getAllProjectionsForTheater(id, pageable);

	}

	@Override
	public Page<String> getHallLabelsForTheater(Long id, Pageable pageable) {
		return this.theaterRepository.getAllHallLabelsForTheater(id, pageable);
	}

	@Override
	public boolean addNewProjectionDate(ProjectionDate pd, Long theater_id, String hall_label, Long projection_id) {
		final String sql = "insert into projection_date(projection_id, hall_label, hall_theater_id, time, date, price) " +
				"values (?,?,?,?,?,?)";
        int inserted = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setLong(1, projection_id);
                ps.setString(2, hall_label);
                ps.setLong(3, theater_id);
                ps.setTime(4, pd.getTime());
                ps.setDate(5, pd.getDate());
                ps.setDouble(6, pd.getPrice());
                return ps;
            }
        });
        return inserted != 0;
	}
}
