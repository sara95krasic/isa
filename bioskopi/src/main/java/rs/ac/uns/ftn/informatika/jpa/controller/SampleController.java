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

import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.TheaterDTO;
import rs.ac.uns.ftn.informatika.jpa.service.TheaterService;

@RestController
public class SampleController {

	@Autowired
	private TheaterService theaterService;
	
	
	@RequestMapping(value = "/search/movietheater/{name}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<TheaterDTO> getMovieTheaterByName(@PathVariable String name) {
		return this.theaterService.getMovieTheaters(name, new PageRequest(0, 10));
	}
	
	@RequestMapping(value = "/allmovietheaters",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<TheaterDTO> getAllMovieTheaters() {
	return this.theaterService.getAllMovieTheaters(new PageRequest(0, 10));
	}	
	
	@RequestMapping(value = "/search/playtheater/{name}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<TheaterDTO> getPlayTheaterByName(@PathVariable String name) {
		return this.theaterService.getPlayTheaters(name, new PageRequest(0, 10));
	}
	
	@RequestMapping(value = "/allplaytheaters",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<TheaterDTO> getAllPlayTheaters() {
	return this.theaterService.getAllPlayTheaters(new PageRequest(0, 10));
	}

	


}
