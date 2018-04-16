package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.ThematicProps;

@Repository
public interface ThematicPropsRepository extends JpaRepository<ThematicProps,Long> {

	ThematicProps findById(Long id);
}
