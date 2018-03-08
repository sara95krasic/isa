package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.Theater;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.TheaterDTO;


public interface TheaterRepository extends Repository<Theater, Long> {

	@Query("select t.id as id, t.name as name, t.address as address, t.description as description, coalesce(avg(r.rating),0) as averageRating " +
			"from Theater t left outer join t.reviews r " +
			"where INSTR(t.name, ?1) > 0 and t.theaterType = 0 " +
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

	@Query("select t.id as id, t.name as name, t.address as address, t.description as description, coalesce(avg(r.rating),0) as averageRating " +
			"from Theater t left outer join t.reviews r " + 
			"where t.theaterType = 0 " +
			"group by t")
	Page<TheaterDTO> getAllMovieThearers(Pageable pageable);

	@Query("select t.id as id, t.name as name, t.address as address, t.description as description, coalesce(avg(r.rating),0) as averageRating " +
			"from Theater t left outer join t.reviews r " +
			"where INSTR(t.name, ?1) > 0 and t.theaterType = 1 " +
			"group by t")
	Page<TheaterDTO> getPlayThearersByName(String name, Pageable pageable);
	
	@Query("select t.id as id, t.name as name, t.address as address, t.description as description, coalesce(avg(r.rating),0) as averageRating " +
			"from Theater t left outer join t.reviews r " + 
			"where t.theaterType = 1 " +
			"group by t")
	Page<TheaterDTO> getAllPlayThearers(Pageable pageable);

	@Query("select t.id as id, t.name as name, t.address as address, t.description as description, coalesce(avg(r.rating),0) as averageRating " +
			"from Theater t left outer join t.reviews r " + 
			"where t.id = ?1 " +
			"group by t")
	TheaterDTO getTheaterById(Long id);

}
