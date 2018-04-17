package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.ThematicProps;
import rs.ac.uns.ftn.informatika.jpa.domain.ThematicPropsType;

@Repository
public interface ThematicPropsRepository extends JpaRepository<ThematicProps,Long> {

	ThematicProps findById(Long id);
	List<ThematicProps> findByTheaterId(Long theaterId);
	List<ThematicProps> findByTheaterIdAndTptype(Long theaterId,ThematicPropsType tptype);
	List<ThematicProps> findByTheaterIdAndTptypeAndCreatedBy(Long heaterId,ThematicPropsType tptype,Long createdBy);
	List<ThematicProps> findByTheaterIdAndTptypeAndCreatedByNot(Long theaterId,ThematicPropsType tptype,Long createdBy);

}
