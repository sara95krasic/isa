package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.MovieTheater;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.MovieTheaterDTO;


public interface MovieTheaterRepository extends Repository<MovieTheater, Long> {

	@Query("select mt.name as name, mt.address as address, mt.description as description, coalesce(avg(r.rating),0) as averageRating "
			+ "from MovieTheater mt left outer join mt.reviews r where INSTR(mt.name, ?1) > 0 group by mt")
	Page<MovieTheaterDTO> getMovieThearersByName(String name, Pageable pageable);
	/*
		select mt.name as name, mt.address as address, mt.description, coalesce(avg(r.rating),0) as averageRating
		from Movie_Theater mt left outer join review r on mt.id = r.movie_theater_id
		where INSTR(mt.name, 'k') > 0
		group by 
		mt.name, mt.address, mt.description
		;
	 */

}
