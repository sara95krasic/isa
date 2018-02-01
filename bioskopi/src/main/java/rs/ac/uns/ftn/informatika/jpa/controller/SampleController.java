package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.MovieTheaterDTO;
import rs.ac.uns.ftn.informatika.jpa.service.MovieTheaterService;

@RestController
public class SampleController {

	@Autowired
	private MovieTheaterService movieTheaterService;
	
	@RequestMapping(value = "/search/movietheater/{name}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<MovieTheaterDTO> getMovieTheaterByName(@PathVariable String name) {
		return this.movieTheaterService.getMovieTheaters(name, new PageRequest(0, 10));
	}
	

}
