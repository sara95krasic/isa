package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.domain.Seat;
import rs.ac.uns.ftn.informatika.jpa.domain.Segment;

public interface SeatService {

	boolean addNewSeat(Seat seat);

	boolean removeSeat(Seat seat);

	List<Seat> getTakenSeats(String segment_label, Long theater_id, String hall_label, Long projection_date_id);

}
