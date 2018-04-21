package rs.ac.uns.ftn.informatika.jpa.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Projection;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDTO;

public interface ProjectionRepository extends Repository<Projection, Long> {

	
	Projection save(Projection p);

	@Query("select p.title as title, p.description as description, p.runtime as runtime, p.id as id, p.director as director, p.poster as poster from Projection p")
	Page<ProjectionDTO> findAll(Pageable pageable);

	@Transactional
	@Modifying (clearAutomatically = true)
	@Query("update Projection p set p.poster = ?2 where p.id = ?1")
	int setPosterImage(Long projection_id, String string);

	@Query("select coalesce(avg(r.rating),0) from Projection p left outer join p.ratings r where p.id = ?1")
	double getAverageRating(Long id);
}
