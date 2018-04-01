package rs.ac.uns.ftn.informatika.jpa.service;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import rs.ac.uns.ftn.informatika.jpa.domain.Theater;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDTO;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDateDTO;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.TheaterDTO;

public interface TheaterService {

	Page<TheaterDTO> getMovieTheaters(String name, Pageable pageable);
	
	Page<TheaterDTO> getAllMovieTheaters(Pageable pageable);
	

	Page<TheaterDTO> getPlayTheaters(String name, Pageable pageable);
	
	Page<TheaterDTO> getAllPlayTheaters(Pageable pageable);

	TheaterDTO getTheaterById(Long id);

	boolean updateTheater(Long id, Theater t);

	Page<Date> getProjectionDatesForTheater(Long id, Pageable pageable);

	Page<ProjectionDTO> getProjectionsForTheaterForDate(Long id, Date date, Pageable pageable);

	Page<ProjectionDateDTO> getProjectionDatesForTheaterForProjection(Long theaterId, Long projectionId, Pageable pageable);

}
