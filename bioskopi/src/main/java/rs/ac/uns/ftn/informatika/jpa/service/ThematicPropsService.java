package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.domain.ThematicProps;
import rs.ac.uns.ftn.informatika.jpa.domain.ThematicPropsType;


public interface ThematicPropsService {
	

	ThematicProps save(ThematicProps thematicProps);
	
	List<ThematicProps> findAll();
	
	ThematicProps deleteThematicProps(Long id);
	
	ThematicProps findById(Long id);
	
	ThematicProps modifyThematicProps(ThematicProps thematicProps,Long id);
	
	List<ThematicProps> findByTheaterId(Long theaterId);
	
	List<ThematicProps> findByTheaterIdAndTptype(Long theaterId,ThematicPropsType tptype);
	
	List<ThematicProps> findByTheaterIdAndTptypeAndCreatedBy(Long theaterId, ThematicPropsType tptype,Long createdBy);
	
	List<ThematicProps> findByTheaterIdAndTptypeAndCreatedByNot(Long theaterId,ThematicPropsType tptype,Long createdBy);
}
