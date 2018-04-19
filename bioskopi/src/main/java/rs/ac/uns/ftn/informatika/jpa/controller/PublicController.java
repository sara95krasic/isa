package rs.ac.uns.ftn.informatika.jpa.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.UserEditForm;
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
	
	
	/*@RequestMapping(value="/login", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> signIn(@RequestBody User user,HttpSession session,HttpServletRequest request){
		User loggedUser = userService.logIn(user);
		if(loggedUser != null) {
		   
		    UserDTO logged = new UserDTO(loggedUser);
		    System.out.println("PROSAO SAM");
		    return new ResponseEntity<UserDTO>(logged,HttpStatus.OK);
		    
		}
		UserDTO logged = null;
		return new ResponseEntity<UserDTO>(logged,HttpStatus.NOT_FOUND);
	}*/
	
	
	@RequestMapping(value = "/registration",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<UserDTO> registerUser(@RequestBody User user) throws Exception{
		System.out.println("*********************" + user.getName() + user.getSurname() + user.getEmail() + user.getPasswordHash());
		userService.create(user);
		User savedUser = userService.findByEmail(user.getEmail());
		userService.sendVerificationMail(savedUser);
		UserDTO userdto = new UserDTO(user);
		return new ResponseEntity<UserDTO>(userdto, HttpStatus.CREATED);
	}
	
	//verifikovanje registracije
	@RequestMapping(value="/verify/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> verifyUser(@PathVariable Long id) throws Exception{
		userService.verifyEmail(id);
		return new ResponseEntity<String>("verifikovan",HttpStatus.ACCEPTED);
	}
	
	//edit
	@RequestMapping(value="/modify/{id}",method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> modifyUser(@RequestBody UserEditForm user, @PathVariable Long id){
		User modified = userService.modifyUser(user, id);
		return new ResponseEntity<UserDTO>(new UserDTO(modified),HttpStatus.OK);
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
		if (u != null) {
			u.setPasswordHash(""); //...
			u.setFriends(null);
			u.setFriendsOf(null);
			u.setReceivedRequests(null);
		}
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
	
	//trazi po imenu ili prezimenu korisnike
	@RequestMapping(value="/search_user_by_name_or_surname",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<UserDTO>> searchUsers(@RequestBody User user){
		System.out.println("RADDim PRETR");
		List<User> searched = userService.findUsers(user.getName(), user.getSurname());
		List<UserDTO> searcheddto = new ArrayList<UserDTO>();
		
		for(User u : searched) {
			searcheddto.add(new UserDTO(u));
			System.out.println("RADDim PRETR" + u.getName() + " " + u.getSurname());
		}
		if(!searched.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(searcheddto,HttpStatus.OK);
		}
		return new ResponseEntity<List<UserDTO>>(searcheddto,HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value="/sendReq/{receiverId}",method = RequestMethod.GET)
	public ResponseEntity<String> sendFriendRequest(@PathVariable Long receiverId,HttpServletRequest request){
		User sender = getCurrentUser(); 	
		userService.sendFriendRequest(sender.getId(), receiverId);
		return new ResponseEntity<String>("zahtev uspesno poslat",HttpStatus.ACCEPTED);
		
	}
	
	@RequestMapping(value="/getRequests/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getRequests(@PathVariable Long id){
		System.out.println("POZVAN SAAAM ZAHTEEVII LOAD");
		List<User> req = userService.getFriendRequests(id);
		List<UserDTO> reqDTO = new ArrayList<UserDTO>();
		for(User request : req) {
			reqDTO.add(new UserDTO(request));
		}
		return new ResponseEntity<List<UserDTO>>(reqDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="/acceptFriendReq/{acceptedId}",method = RequestMethod.GET)
	public ResponseEntity<String> approveFriendRequest(@PathVariable Long acceptedId, HttpServletRequest request){
		User user = getCurrentUser(); 	
		userService.acceptFriendRequest(acceptedId, user.getId());
		return new ResponseEntity<String>("request approved",HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/refuseRequest/{refusedId}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> declineRequest(@PathVariable Long refusedId,HttpServletRequest request){
		User user = getCurrentUser(); 	
		User refused = userService.refuseFriendReq(refusedId, user.getId());
		UserDTO refusedDTO = new UserDTO(refused);
		return new ResponseEntity<UserDTO>(refusedDTO,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getFriends/{id}" , method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getFriends(@PathVariable Long id){
		List<User> friends = userService.getFriends(id);
		List<UserDTO> friendsDTO = new ArrayList<UserDTO>();
		for(User friend : friends) {
			friendsDTO.add(new UserDTO(friend));
		}
		return new ResponseEntity<List<UserDTO>>(friendsDTO,HttpStatus.OK);
	}
	
}
