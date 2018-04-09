package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.domain.CurrentUser;
import rs.ac.uns.ftn.informatika.jpa.domain.User;


@Service
public class CurrentUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//testing
		/*Authentication a = SecurityContextHolder.getContext().getAuthentication();
		if (a != null ) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
			String username = "";
			if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
			System.out.println("zasto se ovo desava??? - " + username);
		}*/
		
		User user = userService.getUserByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));
		
		CurrentUser cu = new CurrentUser(user);
		
		
		//Authentication auth = new UsernamePasswordAuthenticationToken(cu, cu.getPassword(), cu.getAuthorities());
		//SecurityContextHolder.getContext().setAuthentication(auth);
		return cu;
	}

}
