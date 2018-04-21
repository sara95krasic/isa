package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;

public interface ReservationService {

	boolean makeNewReservation(Reservation reservation);

	boolean cancelReservedSeat(Long reservation_id);

	List<Reservation> getAllMyReservations();

}
