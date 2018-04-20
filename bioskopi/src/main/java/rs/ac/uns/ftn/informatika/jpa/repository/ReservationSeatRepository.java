package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.ReservationSeat;
import rs.ac.uns.ftn.informatika.jpa.domain.ReservationSeatId;
import rs.ac.uns.ftn.informatika.jpa.domain.Seat;

public interface ReservationSeatRepository extends Repository<ReservationSeat, ReservationSeatId> {

	@Query("select rs from ReservationSeat rs left outer join rs.reservation r left outer join r.projectionDate pd where rs.seat = ?2 and pd.id = ?1")
	ReservationSeat findOneByProjectionDateIdAndSeat(Long id, Seat seat);

	@Transactional
	@Modifying
	@Query("delete from ReservationSeat rs where rs=?1")
	void delete(ReservationSeat rs);
	
}
