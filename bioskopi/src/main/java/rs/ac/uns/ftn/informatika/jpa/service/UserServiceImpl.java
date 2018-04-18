package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.domain.CurrentUser;
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
	public User logIn(User user) {
		User userToLog = userRepository.findByEmail(user.getEmail());
		System.out.println("-------------" + user.getEmail() + user.getPasswordHash() );
		
		if(userToLog != null) {
			if(new BCryptPasswordEncoder().matches(user.getPasswordHash(), userToLog.getPasswordHash()) && userToLog.isVerification() == true) {
				return userToLog;
			}
		}
		return null;
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

	@Override
	public List<User> findUsers(String name, String surname) {
		
		List<User> by_name = new ArrayList<User>();
		List<User> by_surname = new ArrayList<User>();
		
		//nadji sve po imenu
		if(!name.isEmpty())
			by_name = userRepository.findByNameContainingAllIgnoringCase(name);
		//ako nije zadato prezime, samo vrati te po imenu
		if (surname.isEmpty())
			return by_name;
		
		//ako je zadato prezime, trazi po njemu
		if(!surname.isEmpty())
			by_surname = userRepository.findBySurnameContainingAllIgnoringCase(surname);
		//ako nismo trazili po imenu (jer nije zadato), samo vrati ove nadjene po prezimenu
		if (name.isEmpty())
			return by_surname;
		
		
		//jbg, ako smo stigli do ovde onda je trazeno i po imenu i prz
		//spoji te 2 liste u double_match
		List<User> double_match = new ArrayList<User>();
		for (User u : by_name)
			for (User uu : by_surname)
				if (u.getId() == uu.getId())
					double_match.add(uu);
		
		return double_match;
	}

	@Override
	public boolean changePassword(String old_pass, String new_pass) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = null;
		if (auth != null && auth.getPrincipal() instanceof CurrentUser)
			u = ((CurrentUser)auth.getPrincipal()).getUser();
		if (u == null) return false;
		
		if(!new_pass.isEmpty() && !old_pass.isEmpty() &&
				new BCryptPasswordEncoder().matches(old_pass, u.getPasswordHash())){			
			User u_iz_baze = userRepository.findOne(u.getId());
			u_iz_baze.setPasswordHash(new BCryptPasswordEncoder().encode(new_pass));
			u_iz_baze.setHasLoggedInBefore(true);
			userRepository.save(u_iz_baze);
			
			//usput,...
			((CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().setPasswordHash(u_iz_baze.getPasswordHash());
			((CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().setHasLoggedInBefore(u_iz_baze.isHasLoggedInBefore());
			
			return true;
		}
		return false;
	}

	
		
	
	
}
