package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.domain.Role;
import rs.ac.uns.ftn.informatika.jpa.service.SessionService;
import rs.ac.uns.ftn.informatika.jpa.service.UserService;

@RestController
@RequestMapping("/system_administration")
public class SystemAdministrationController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "set_role/{user_id}/{role}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean setRole(@PathVariable Long user_id, @PathVariable Role role) {
		if (user_id == SessionService.getCurrentlyLoggedUser().getId())
			return false; //ne mozes sam sebi menjati role
		return this.userService.setRole(user_id, role);
	}
}
