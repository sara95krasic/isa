package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.User;

import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.UserDTO;


public interface UserRepository extends Repository<User, Long>{

	User findOne(Long id);
	
	User save(User user);
	
	Optional<User> findOneByEmail(String email);
	
	User findByEmail(String email);
	
	List<User> findByNameContainingAllIgnoringCase(String name);
	
	List<User> findBySurnameContainingAllIgnoringCase(String surname);

	List<User> findAll();

	

}
