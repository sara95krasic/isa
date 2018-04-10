package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.Seat;
import rs.ac.uns.ftn.informatika.jpa.domain.SeatId;

public interface SeatRepository extends Repository<Seat, SeatId> {
	
}
