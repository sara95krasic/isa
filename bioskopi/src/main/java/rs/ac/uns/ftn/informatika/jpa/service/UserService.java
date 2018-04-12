package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.Optional;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.UserEditForm;

public interface UserService {
	Optional<User> getUserByEmail(String email);

	User create(User user);
	
	User save(User user);
	
	User findByEmail(String email);
	
	void sendVerificationMail(User user);
	
	boolean verifyEmail(Long id);
	
	User modifyUser(UserEditForm user, Long id);
}
