package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.Optional;

import rs.ac.uns.ftn.informatika.jpa.domain.User;

public interface UserService {
	Optional<User> getUserByEmail(String email);

	User create(User user);
	
	User save(User user);
	
	User findByEmail(String email);
	
	void sendVerificationMail(User user);
	
	boolean verifyEmail(Long id);
}
