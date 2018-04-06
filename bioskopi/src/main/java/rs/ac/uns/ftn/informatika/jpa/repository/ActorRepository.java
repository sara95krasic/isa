package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.Actor;

public interface ActorRepository extends Repository<Actor, String> {
	
	Actor save(Actor a);

	Actor findOne(String name);
}
