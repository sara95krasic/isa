package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import rs.ac.uns.ftn.informatika.jpa.domain.CurrentUser;
import rs.ac.uns.ftn.informatika.jpa.domain.User;

public class SessionService {

	public static User getCurrentlyLoggedUser() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getPrincipal() instanceof CurrentUser) {
			return ((CurrentUser)auth.getPrincipal()).getUser();
		}
		return null;
	}
}
