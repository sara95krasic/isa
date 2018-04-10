package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.TheaterDTO;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.UserDTO;
import rs.ac.uns.ftn.informatika.jpa.service.TheaterService;
import rs.ac.uns.ftn.informatika.jpa.service.UserService;

/**
 * Kontroler dostupan svim korisnicima.
 * Uglavnom sadrzi funcije namenjene neregirstrovanim korisnicima.
 * @author jerem
 *
 */
@RestController
@RequestMapping("/public")
public class PublicController {
	

	@Autowired
	private TheaterService theaterService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/registration",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<UserDTO> registerUser(@RequestBody User user) throws Exception{
		System.out.println("*********************" + user.getName() + user.getSurname() + user.getEmail() + user.getPasswordHash());
		userService.create(user);
		User savedUser = userService.findByEmail(user.getEmail());
		//userService.sendVerificationMail(savedUser);
		UserDTO userdto = new UserDTO(user);
		return new ResponseEntity<UserDTO>(userdto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Vraca trenutno ulogovanog korisnika (sve podatke sem passworda)
	 * (mozda ograniciti sa '@PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")') ???
	 * @return
	 */
	@RequestMapping(value="/get_current_user",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User u = userService.getUserByEmail(email).orElse(null);
		if (u != null)
			u.setPasswordHash(""); //...
		return u;
	}
	
	/**
	 * Pronalazi sve bioskope koji u imenu sadrze 'name'
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/get_movie_theater_by_name/{name}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<TheaterDTO> getMovieTheaterByName(@PathVariable String name) {
	return this.theaterService.getMovieTheaters(name, new PageRequest(0, 10));
	}
	
	/**
	 * Vraca sve bioskope
	 * @return
	 */
	@RequestMapping(value = "/get_all_movie_theaters",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<TheaterDTO> getAllMovieTheaters() {
	return this.theaterService.getAllMovieTheaters(new PageRequest(0, 10));
	}	
	
	/**
	 * Pronalazi sva pozorista koji u imenu sadrze 'name'
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/get_play_theater_by_name/{name}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<TheaterDTO> getPlayTheaterByName(@PathVariable String name) {
	return this.theaterService.getPlayTheaters(name, new PageRequest(0, 10));
	}
	
	/**
	 * Vraca sva pozorista
	 * @return
	 */
	@RequestMapping(value = "/get_all_play_theaters",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<TheaterDTO> getAllPlayTheaters() {
	return this.theaterService.getAllPlayTheaters(new PageRequest(0, 10));
	}
	
	/**
	 * Pronalazi jedan bioskop po id-u
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "get_theater_by_id/{id}",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TheaterDTO getTheaterById(@PathVariable Long id) {
	return this.theaterService.getTheaterById(id);
	}
}
