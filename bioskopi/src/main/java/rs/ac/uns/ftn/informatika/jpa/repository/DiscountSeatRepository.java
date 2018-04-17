package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.DiscountSeat;
import rs.ac.uns.ftn.informatika.jpa.domain.DiscountSeatId;
import rs.ac.uns.ftn.informatika.jpa.domain.Seat;

public interface DiscountSeatRepository extends Repository<DiscountSeat, DiscountSeatId> {

	@Query("select ds from DiscountSeat ds left outer join ds.projectionDate pd where ds.seat = ?1 and pd.id = ?2")
	DiscountSeat findOneBySeatAndProjectionDateId(Seat seat, Long projection_date_id);

	@Transactional
	@Modifying
	@Query("delete from DiscountSeat ds where ds = ?1")
	int delete(DiscountSeat discSeat);
	
}
