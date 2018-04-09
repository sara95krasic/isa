package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}
	
	@Override
	public User create(User user) {
		user.setPasswordHash(new BCryptPasswordEncoder().encode(user.getPasswordHash())); //primljeni user (iz html stranice) ima normalan pass, ovde ga enkodujem
		return userRepository.save(user);
	}
	
}
