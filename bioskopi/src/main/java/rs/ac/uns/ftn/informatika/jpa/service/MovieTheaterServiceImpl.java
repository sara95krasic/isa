package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.MovieTheaterDTO;
import rs.ac.uns.ftn.informatika.jpa.repository.MovieTheaterRepository;

@Service
public class MovieTheaterServiceImpl implements MovieTheaterService {

	
	@Autowired
	private MovieTheaterRepository movieTheaterRepository;

	@Override
	public Page<MovieTheaterDTO> getMovieTheaters(String name, Pageable pageable) {
		Assert.notNull(name, "Name ne sme biti null");
		return this.movieTheaterRepository.getMovieThearersByName(name, pageable);
	}
}
