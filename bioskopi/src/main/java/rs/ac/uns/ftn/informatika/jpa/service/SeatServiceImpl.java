package rs.ac.uns.ftn.informatika.jpa.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.repository.DiscountSeatRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReservationSeatRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.SeatRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.SegmentRepository;
import rs.ac.uns.ftn.informatika.jpa.domain.DiscountSeat;
import rs.ac.uns.ftn.informatika.jpa.domain.ReservationSeat;
import rs.ac.uns.ftn.informatika.jpa.domain.Seat;
import rs.ac.uns.ftn.informatika.jpa.domain.Segment;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	SeatRepository seatRepository;
	@Autowired
	SegmentRepository segmentRepository;
	@Autowired
	ReservationSeatRepository reservationSeatRepository;
	@Autowired
	DiscountSeatRepository discountSeatRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	@Override
	public boolean addNewSeat(Seat seat) {
		final String sql = "insert into seat (segment_hall_theater_id, segment_hall_label, segment_label, row, number) values (?, ?, ?, ?, ?)";
        int inserted = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setLong(1, seat.getSegment().getHall().getTheater().getId());
                ps.setString(2, seat.getSegment().getHall().getLabel());
                ps.setString(3, seat.getSegment().getLabel());
                ps.setInt(4, seat.getRow());
                ps.setInt(5, seat.getNumber());
                return ps;
            }
        });
        return inserted != 0;
	}
	
	@Override
	public boolean removeSeat(Seat seat) {
		final String sql = "delete from seat where segment_hall_theater_id = ? and segment_hall_label = ? and segment_label = ? and row = ? and number = ?";
        int deleted = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setLong(1, seat.getSegment().getHall().getTheater().getId());
                ps.setString(2, seat.getSegment().getHall().getLabel());
                ps.setString(3, seat.getSegment().getLabel());
                ps.setInt(4, seat.getRow());
                ps.setInt(5, seat.getNumber());
                return ps;
            }
        });
		return deleted != 0;
	}

	@Override
	public List<Seat> getTakenSeats(String segment_label, Long theater_id, String hall_label, Long projection_date_id) {
		List<Seat> ret = new ArrayList<Seat>();
		
		Segment seg = segmentRepository.findSegmentByLabelForHallForTheater(segment_label, theater_id, hall_label);
		for (Seat seat : seg.getSeats()) {
			ReservationSeat rs = reservationSeatRepository.findOneByProjectionDateIdAndSeat(projection_date_id, seat);	//da li je neko vec rezervisao?
			DiscountSeat ds = discountSeatRepository.findOneBySeatAndProjectionDateId(seat, projection_date_id);		//da li je ovo mesto na popustu?
			if (rs != null || ds != null)
				ret.add(seat);
		}
		
		for (Seat seat : ret) {
			seat.setSegment(null);
		}
		
		return ret;
	}

}
