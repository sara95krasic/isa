package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.ThematicProps;
import rs.ac.uns.ftn.informatika.jpa.repository.ThematicPropsRepository;

@Service
@Transactional
public class ThematicPropsServiceImpl implements ThematicPropsService {

	@Autowired
	private ThematicPropsRepository thematicPropsRepository;

	@Override
	public ThematicProps save(ThematicProps thematicProps) {
		return thematicPropsRepository.save(thematicProps);
	}

	@Override
	public List<ThematicProps> findAll() {
		return thematicPropsRepository.findAll();
	}

	@Override
	public ThematicProps deleteThematicProps(Long id) {
		ThematicProps thematicProps = thematicPropsRepository.findById(id);
		thematicPropsRepository.delete(thematicProps);
		return thematicProps;
	}

	@Override
	public ThematicProps findById(Long id) {		
		return thematicPropsRepository.findById(id);
	}

	@Override
	public ThematicProps modifyThematicProps(ThematicProps thematicProps, Long id) {
		ThematicProps oldThematicProps = thematicPropsRepository.findById(id);
		if(thematicProps.getName() != null) {
			oldThematicProps.setName(thematicProps.getName());
		}
		if(thematicProps.getDescription()!=null) {
			oldThematicProps.setDescription(thematicProps.getDescription());
		}
		if(thematicProps.getDate()!=null) {
			oldThematicProps.setDate(thematicProps.getDate());
		}
		if(thematicProps.getPicture()!=null) {
			oldThematicProps.setPicture(thematicProps.getPicture());
		}
		if(thematicProps.getTptype() != null) {
			oldThematicProps.setTptype(thematicProps.getTptype());
		}
		return thematicPropsRepository.save(oldThematicProps);
	}

}
