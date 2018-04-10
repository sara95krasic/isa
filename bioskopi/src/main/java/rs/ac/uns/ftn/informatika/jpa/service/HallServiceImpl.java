package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.repository.HallRepository;

@Service
public class HallServiceImpl implements HallService {

	@Autowired
	private HallRepository hallRepository;
	
	@Override
	public boolean deleteHallByLabelForTheater(Long theater_id, String hall_label) {
		int ret = hallRepository.deleteByTheaterIdAndHallLabel(theater_id, hall_label);
		return ret != 0;
	}
}
