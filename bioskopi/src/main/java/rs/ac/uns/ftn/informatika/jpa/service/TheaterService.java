package rs.ac.uns.ftn.informatika.jpa.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rs.ac.uns.ftn.informatika.jpa.domain.DiscountSeat;
import rs.ac.uns.ftn.informatika.jpa.domain.ProjectionDate;
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

	Page<ProjectionDTO> getProjectionsForTheater(Long id, Pageable pageable);

	Page<String> getHallLabelsForTheater(Long id, Pageable pageable);

	boolean addNewProjectionDate(ProjectionDate pd, Long theater_id, String hall_label, Long projection_id);

	List<DiscountSeat> getDiscountSeatsForTheater(Long id, Pageable pageable);

}
