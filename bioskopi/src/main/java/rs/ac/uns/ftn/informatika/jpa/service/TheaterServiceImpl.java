package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.TheaterDTO;
import rs.ac.uns.ftn.informatika.jpa.repository.TheaterRepository;

@Service
public class TheaterServiceImpl implements TheaterService {

	
	@Autowired
	private TheaterRepository theaterRepository;

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
}
