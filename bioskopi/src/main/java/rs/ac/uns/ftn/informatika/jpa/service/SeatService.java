package rs.ac.uns.ftn.informatika.jpa.service;

import rs.ac.uns.ftn.informatika.jpa.domain.Seat;

public interface SeatService {

	boolean addNewSeat(Seat seat);

	boolean removeSeat(Seat seat);

}
