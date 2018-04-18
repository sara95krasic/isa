package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.UserEditForm;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.UserDTO;

public interface UserService {
	Optional<User> getUserByEmail(String email);

	User create(User user);
	
	User logIn(User user);
	
	User save(User user);
	
	User findByEmail(String email);
	
	void sendVerificationMail(User user);
	
	boolean verifyEmail(Long id);
	
	User modifyUser(UserEditForm user, Long id);
	
	List<User> findUsers(String name,String surname);

	boolean changePassword(String old_pass, String new_pass);
}
