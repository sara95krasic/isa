package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Segment;
import rs.ac.uns.ftn.informatika.jpa.domain.SegmentId;

public interface SegmentRepository extends Repository<Segment, SegmentId> {

	@Query("select s from Segment s left outer join s.hall h left outer join h.theater t " +
			"where t.id = ?1 and h.label = ?2")
	List<Segment> findAllByTheaterIdAndHallLabel(Long theater_id, String hall_label, Pageable pageable);

	@Query("select s from Segment s left outer join s.hall h left outer join h.theater t " +
			"where s.label = ?1 and t.id = ?2 and h.label = ?3")
	Segment findSegmentByLabelForHallForTheater(String segment_label, Long theater_id, String hall_label);



	//@Transactional
	//@Modifying
	//@Query("delete from Segment s where s.label=?1 and s.theater.id = ?2 and s.theater.id in (select t.id from Theater t left outer join t.halls  where h.label = ?3)")
	//int deleteByLabelAndTheaterIdAndHallLabel(String segment_label, Long theater_id, String hall_label);
	
}
