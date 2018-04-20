package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;

public interface ReservationRepository extends Repository<Reservation, Long> {

	Reservation findOne(Long reservation_id);

	Reservation save(Reservation r);

}
