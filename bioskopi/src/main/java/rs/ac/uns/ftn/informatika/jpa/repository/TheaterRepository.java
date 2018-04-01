package rs.ac.uns.ftn.informatika.jpa.repository;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Theater;
import rs.ac.uns.ftn.informatika.jpa.domain.TheaterType;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDTO;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDateDTO;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.TheaterDTO;


public interface TheaterRepository extends Repository<Theater, Long> {

	@Query("select t.id as id, t.theaterType as theaterType, t.name as name, t.address as address, t.description as description, coalesce(avg(r.rating),0) as averageRating " +
			"from Theater t left outer join t.reviews r " +
			"where INSTR(t.name, ?1) > 0 and t.theaterType = 'MOVIE_THEATER' " +
			"group by t")
	Page<TheaterDTO> getMovieThearersByName(String name, Pageable pageable);
	/*
		select mt.name as name, mt.address as address, mt.description, coalesce(avg(r.rating),0) as averageRating
		from Movie_Theater mt left outer join review r on mt.id = r.movie_theater_id
		where INSTR(mt.name, 'k') > 0
		group by 
		mt.name, mt.address, mt.description
		;
	 */

	@Query("select t.id as id, t.theaterType as theaterType, t.name as name, t.address as address, t.description as description, coalesce(avg(r.rating),0) as averageRating " +
			"from Theater t left outer join t.reviews r " + 
			"where t.theaterType = 'MOVIE_THEATER' " +
			"group by t")
	Page<TheaterDTO> getAllMovieThearers(Pageable pageable);

	@Query("select t.id as id, t.theaterType as theaterType, t.name as name, t.address as address, t.description as description, coalesce(avg(r.rating),0) as averageRating " +
			"from Theater t left outer join t.reviews r " +
			"where INSTR(t.name, ?1) > 0 and t.theaterType = 'PLAY_THEATER' " +
			"group by t")
	Page<TheaterDTO> getPlayThearersByName(String name, Pageable pageable);
	
	@Query("select t.id as id, t.theaterType as theaterType, t.name as name, t.address as address, t.description as description, coalesce(avg(r.rating),0) as averageRating " +
			"from Theater t left outer join t.reviews r " + 
			"where t.theaterType = 'PLAY_THEATER' " +
			"group by t")
	Page<TheaterDTO> getAllPlayThearers(Pageable pageable);

	@Query("select t.id as id, t.theaterType as theaterType, t.name as name, t.address as address, t.description as description, coalesce(avg(r.rating),0) as averageRating " +
			"from Theater t left outer join t.reviews r " + 
			"where t.id = ?1 " +
			"group by t")
	TheaterDTO getTheaterById(Long id);

	//spring data jpa
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Theater t set t.name = ?2, t.address = ?3, t.description = ?4, t.theaterType = ?5 " +
			"where t.id = ?1")
	int updateTheaterById(Long id, String name, String address, String description, TheaterType theaterType);

	@Query("select distinct pd.date from ProjectionDate pd left outer join pd.hall h where h.theater.id = ?1")
	Page<Date> getAllProjectionDatesForTheater(Long id, Pageable pageable);
	
	@Query("select distinct p.title as title, p.description as description, p.runtime as runtime, p.id as id, p.director as director " +
			"from ProjectionDate pd left outer join pd.projection p left outer join pd.hall h " +
			"where h.theater.id = ?1 and pd.date = ?2")
	Page<ProjectionDTO> getAllProjectionsForTheaterForDate(Long id, Date date, Pageable pageable);

	@Query("select pd.date as date, pd.time as time, pd.price as price, coalesce(pd.discount, 0) as discount, h.label as hallLabel "+
			"from ProjectionDate pd left outer join pd.hall h left outer join pd.projection p " +
			"where h.theater.id = ?1 and p.id = ?2")
	Page<ProjectionDateDTO> getAllProjectionDatesForTheaterForProjection(Long theaterId, Long projectionId, Pageable pageable);

}
