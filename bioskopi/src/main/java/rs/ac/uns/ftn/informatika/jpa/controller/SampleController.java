package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.service.ProjectionService;
import rs.ac.uns.ftn.informatika.jpa.service.TheaterService;

@RestController
@RequestMapping("/test")
public class SampleController {

	@Autowired
	private TheaterService theaterService;
	@Autowired
	private ProjectionService projectionService;
	
	@RequestMapping(value = "/asdf", 
			method = RequestMethod.GET)
	public String showHomePage() {
	    return "theater";
	}
	
	
	@RequestMapping(value="/get_logged_user",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String printUser() {
	 
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
	      //name = name + " - " + auth.getPrincipal();
			
	      return name;
	 
	  }
}
