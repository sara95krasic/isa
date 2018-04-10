package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.User;

public interface UserRepository extends Repository<User, Long>{

	User findOne(Long id);
	
	User save(User user);
	
	Optional<User> findOneByEmail(String email);
	
	User findByEmail(String email);
}
