package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;

public interface ReservationRepository extends Repository<Reservation, Long> {

	Reservation findOne(Long reservation_id);

	Reservation save(Reservation r);

	@Query("select r from Reservation r where r.reservedBy.id = ?1")
	List<Reservation> getAllReservationsForUser(Long id);

}
