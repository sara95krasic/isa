package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.domain.DiscountSeat;
import rs.ac.uns.ftn.informatika.jpa.domain.Seat;
import rs.ac.uns.ftn.informatika.jpa.repository.DiscountSeatRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.SeatRepository;

@Service
public class DiscountSeatServiceImpl implements DiscountSeatService {

	@Autowired
	DiscountSeatRepository discountSeatRepository;
	@Autowired
	SeatRepository seatRepository;
	
	
	@Override
	public boolean deleteDiscountSeat(DiscountSeat discountSeat) {

		Seat seat = seatRepository.findOne(discountSeat.getSeat().getSegment().getHall().getTheater().getId(), 
				discountSeat.getSeat().getSegment().getHall().getLabel(),
				discountSeat.getSeat().getSegment().getLabel(),
				discountSeat.getSeat().getRow(),
				discountSeat.getSeat().getNumber());
		DiscountSeat discSeatActualOne = discountSeatRepository.findOneBySeatAndProjectionDateId(seat, discountSeat.getProjectionDate().getId());
		int deleted = discountSeatRepository.delete(discSeatActualOne);
		return deleted != 0;
	}

}
