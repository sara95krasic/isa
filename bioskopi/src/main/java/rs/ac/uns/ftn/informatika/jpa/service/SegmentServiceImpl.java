package rs.ac.uns.ftn.informatika.jpa.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.domain.Seat;
import rs.ac.uns.ftn.informatika.jpa.domain.Segment;
import rs.ac.uns.ftn.informatika.jpa.repository.SegmentRepository;;

@Service
public class SegmentServiceImpl implements SegmentService {

	@Autowired
	private SegmentRepository segmentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    
	@Override
	public List<Segment> getAllSegmentsForHallForTheater(Long theater_id, String hall_label, Pageable pageable) {
		List<Segment> ret = segmentRepository.findAllByTheaterIdAndHallLabel(theater_id, hall_label, pageable);
		
		//ciscenje da u listi ostanu samo imena i sedista, ovo ostalo ce napraviti haos kad bude serijalizovano u json
		for (Segment seg : ret) {
			seg.setHall(null);
			for (Seat seat : seg.getSeats()) {
				seat.setSegment(null);
			}
		}
		
		
		return ret;
	}
	

	@Override
	public boolean addNewSegment(Segment segment) {
		final String sql = "insert into segment (hall_theater_id, hall_label, label, segment_type) values (?, ?, ?, ?)";
        int inserted = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setLong(1, segment.getHall().getTheater().getId());
                ps.setString(2, segment.getHall().getLabel());
                ps.setString(3, segment.getLabel());
                ps.setString(4, segment.getSegmentType().toString());
                return ps;
            }
        });
        return inserted != 0;
	}


	@Override
	public boolean deleteSegmentByLabelForHallForTheater(Long theater_id, String hall_label, String segment_label) {
		//int ret = segmentRepository.deleteByLabelAndTheaterIdAndHallLabel(segment_label, theater_id, hall_label);
		final String sql = "delete from segment where label = ? and hall_label = ? and hall_theater_id = ?";
        int deleted = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, segment_label);
                ps.setString(2, hall_label);
                ps.setLong(3, theater_id);
                return ps;
            }
        });
		return deleted != 0;
	}


	@Override
	public Segment getSegmentByLabelForHallForTheater(String segment_label, Long theater_id, String hall_label) {
		Segment ret = segmentRepository.findSegmentByLabelForHallForTheater(segment_label, theater_id, hall_label);
		
		//da ne pravi problem kad generise json
		ret.setHall(null);
		for (Seat seat : ret.getSeats()) {
			seat.setSegment(null);
		}
		
		return ret;
	}
	
}
