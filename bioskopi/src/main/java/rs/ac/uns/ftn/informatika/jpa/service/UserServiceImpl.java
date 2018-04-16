package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import rs.ac.uns.ftn.informatika.jpa.domain.Role;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.UserEditForm;
import rs.ac.uns.ftn.informatika.jpa.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private JavaMailSender javaMailSender;
	
	
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
		try {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(user.getEmail());
			email.setFrom("krasicsara1@gmail.com");
			email.setSubject("Link za verifikaciju naloga");
			email.setText("Pozdrav " + user.getName() + ",\n\n http://localhost:8090/public/verify/"+user.getId()+"");
			javaMailSender.send(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean verifyEmail(Long id) {
		User user = userRepository.findOne(id);
		user.setVerification(true);
		userRepository.save(user);
		return true;
	}
	
	@Override
	public User modifyUser(UserEditForm userFormData, Long id) {
		User userToModify = userRepository.findOne(id);
		
		if(userFormData.getName() != null && !userFormData.getName().isEmpty()){
			userToModify.setName(userFormData.getName());
		}
		
		if(userFormData.getSurname()!=null && !userFormData.getName().isEmpty()){
			userToModify.setSurname(userFormData.getSurname());
		}
		
		if(userFormData.getCity()!=null && !userFormData.getName().isEmpty()){
			userToModify.setCity(userFormData.getCity());
		}
		
		if(userFormData.getPhone()!=null && !userFormData.getName().isEmpty()){
			userToModify.setPhone(userFormData.getPhone());
		}
		
		if(userFormData.getPasswordNew() != null && !userFormData.getPasswordNew().isEmpty() &&
				userFormData.getPasswordOld() != null && !userFormData.getPasswordOld().isEmpty() &&
				new BCryptPasswordEncoder().matches(userFormData.getPasswordOld(), userToModify.getPasswordHash())){			
			userToModify.setPasswordHash(new BCryptPasswordEncoder().encode(userFormData.getPasswordNew()));
		}
		
		return userRepository.save(userToModify);
	}
	
}
