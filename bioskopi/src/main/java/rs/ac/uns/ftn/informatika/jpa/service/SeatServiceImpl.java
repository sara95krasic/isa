package rs.ac.uns.ftn.informatika.jpa.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.repository.SeatRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Seat;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	SeatRepository seatRepository;

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

}
