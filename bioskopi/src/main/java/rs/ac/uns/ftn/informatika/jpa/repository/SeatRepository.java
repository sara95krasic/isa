package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.Seat;
import rs.ac.uns.ftn.informatika.jpa.domain.SeatId;

public interface SeatRepository extends Repository<Seat, SeatId> {
	
	@Query("select s from Seat s left outer join s.segment seg left outer join seg.hall h left outer join h.theater t "+
			"where t.id = ?1 and h.label = ?2 and seg.label = ?3 and s.row = ?4 and s.number = ?5")
	Seat findOne(Long theater_id, String hall_label, String segment_label, int row, int number);
	
}
