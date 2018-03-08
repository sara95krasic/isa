package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.TheaterDTO;

public interface TheaterService {

	Page<TheaterDTO> getMovieTheaters(String name, Pageable pageable);
	
	Page<TheaterDTO> getAllMovieTheaters(Pageable pageable);
	

	Page<TheaterDTO> getPlayTheaters(String name, Pageable pageable);
	
	Page<TheaterDTO> getAllPlayTheaters(Pageable pageable);

	TheaterDTO getTheaterById(Long id);

}
