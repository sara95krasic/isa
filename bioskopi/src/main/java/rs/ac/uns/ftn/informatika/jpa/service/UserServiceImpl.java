package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;

import rs.ac.uns.ftn.informatika.jpa.domain.Role;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment enviroment;
	
	
	@Override
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}
	
	@Override
	public User create(User user) {
		user.setPasswordHash(new BCryptPasswordEncoder().encode(user.getPasswordHash())); //primljeni user (iz html stranice) ima normalan pass, ovde ga enkodujem
		user.setRole(Role.USER);
		return userRepository.save(user);
	}
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	@Async
	public void sendVerificationMail(User user) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(enviroment.getProperty("spring.mail.username"));
		mail.setSubject("Link za verifikaciju naloga");
		mail.setText("Pozdrav " + user.getName() + ",\n\n http://localhost:8080/api/users/verify/"+user.getId()+"");
		javaMailSender.send(mail);
	}
	
}
