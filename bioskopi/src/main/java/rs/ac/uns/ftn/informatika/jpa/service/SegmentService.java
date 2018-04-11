package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import rs.ac.uns.ftn.informatika.jpa.domain.Segment;

public interface SegmentService {

	List<Segment> getAllSegmentsForHallForTheater(Long theater_id, String hall_label, Pageable pageable);

	boolean addNewSegment(Segment segment);

	boolean deleteSegmentByLabelForHallForTheater(Long theater_id, String hall_label, String segment_label);

	Segment getSegmentByLabelForHallForTheater(String segment_label, Long theater_id, String hall_label);

	List<Segment> getAllSegmentsForProjectionDate(Long projection_date_id, Pageable pageable);
	
}
