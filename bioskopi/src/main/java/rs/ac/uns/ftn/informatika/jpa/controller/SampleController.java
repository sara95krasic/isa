package rs.ac.uns.ftn.informatika.jpa.controller;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.domain.Theater;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDTO;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDateDTO;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.TheaterDTO;
import rs.ac.uns.ftn.informatika.jpa.service.TheaterService;

@RestController
public class SampleController {

	@Autowired
	private TheaterService theaterService;
	
	@RequestMapping(value = "/test", 
			method = RequestMethod.GET)
	public String showHomePage() {
	    return "theater";
	}
	
	
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

	@RequestMapping(value = "get_theater_by_id/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TheaterDTO getTheaterById(@PathVariable Long id) {
	return this.theaterService.getTheaterById(id);
	}
	
	@RequestMapping(value = "update_theater/{id}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean UpdateTheater(@PathVariable Long id, @RequestBody Theater t) {
		//TODO: validiraj da je to admin pozorista ko updateuje
		return this.theaterService.updateTheater(id, t);
	}
	
	@RequestMapping(value = "get_projection_dates_for_theater/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<Date> GetProjectionDatesForTheater(@PathVariable Long id) {
		return this.theaterService.getProjectionDatesForTheater(id, new PageRequest(0, 10));
	}
	
	
	@RequestMapping(value = "get_projections_for_theater_for_date/{id}/{date}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<ProjectionDTO> GetProjectionsForTheater(@PathVariable Long id, @PathVariable Date date) {
		return this.theaterService.getProjectionsForTheaterForDate(id, date, new PageRequest(0, 10));
	}
	
	@RequestMapping(value = "get_projectiondates_for_theater_for_projection/{theaterId}/{projectionId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<ProjectionDateDTO> GetProjectionDatesForTheater(@PathVariable Long theaterId, @PathVariable Long projectionId) {
		return this.theaterService.getProjectionDatesForTheaterForProjection(theaterId, projectionId, new PageRequest(0, 10));
	}
	
	
	

}
