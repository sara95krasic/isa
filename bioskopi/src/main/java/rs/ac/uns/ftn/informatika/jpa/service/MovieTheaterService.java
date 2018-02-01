package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.MovieTheaterDTO;

public interface MovieTheaterService {

	Page<MovieTheaterDTO> getMovieTheaters(String name, Pageable pageable);

}
