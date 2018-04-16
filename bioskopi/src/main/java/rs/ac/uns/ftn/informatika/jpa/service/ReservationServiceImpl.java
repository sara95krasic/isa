package rs.ac.uns.ftn.informatika.jpa.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Statement;

import rs.ac.uns.ftn.informatika.jpa.domain.DiscountSeat;
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.ReservationSeat;
import rs.ac.uns.ftn.informatika.jpa.domain.Seat;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.repository.DiscountSeatRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReservationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReservationSeatRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.SeatRepository;;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	ReservationSeatRepository reservationSeatRepository;
	@Autowired
	SeatRepository seatRepository;
	@Autowired
	DiscountSeatRepository discountSeatRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean makeNewReservation(Reservation reservation) {
		
		//ako je ovo sediste rezervisano, treba ga posle izbrisati
		//naravno, mozda ovaj uopste ne pokusava da rezervise mesto na popustu
		//onda se ovo i ne koristi
		DiscountSeat discSeat = null; 
		
		//prvo neke provere za ova sedista
		for (ReservationSeat rs : reservation.getReservationSeats()) {
			
			//mozda je vec rezervisano to mesto?
			ReservationSeat postoji = reservationSeatRepository.findOneByProjectionDateIdAndSeat(reservation.getProjectionDate().getId(), rs.getSeat());
			if (postoji != null) {
				return false; //vec je rezervisano to mesto
			}
			
			//popust?
			if (rs.getDiscount() != 0) {
				//ako je popust != 0, onda je mozda preko brze rezervacije poslato, i to je okej
				//al mozda neko pokusava da ubaci popus na kvarno (preko poslatog jsona)
				//TODO; za svaki slucaj, u tom slucaju bi lista reservationSeats smela da ima samo jedno sediste (brza rezervacija)
				Seat sss = seatRepository.findOne(rs.getSeat().getSegment().getHall().getTheater().getId(), 
						rs.getSeat().getSegment().getHall().getLabel(), 
						rs.getSeat().getSegment().getLabel(),
						rs.getSeat().getRow(),
						rs.getSeat().getNumber());
				discSeat = discountSeatRepository.findOneBySeatAndProjectionDateId(sss, reservation.getProjectionDate().getId());
				//to je kao to sediste na popustu
				if (rs.getDiscount() != discSeat.getDiscount()) {
					//to nije taj popust, sorry
					return false;
				}
			}
			
		}
		//sve okej...
		
		
		//ovaj sto je trenutno ulogovan, on i pravi rezervaciju
		User u = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
		
		
		final String sql = "insert into reservation (projection_date_id, reserved_by_id) values (?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();
        int inserted1 = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, reservation.getProjectionDate().getId());
                ps.setLong(2, u.getId());
                return ps;
            }
        }, holder);
        //u holderu je sacuvan kljuc unete rezervacije, trebace da dodamo i rezervisana sedista
        
		for (ReservationSeat rs : reservation.getReservationSeats()) {
       
			//upisi mesto
			final String sql2 = "insert into reservation_seat ( " +
					"reservation_id, " + //za koju rezervaciju
					"seat_number, seat_row, seat_segment_label, seat_segment_hall_label, seat_segment_hall_theater_id, " + //full id sedista (jbg)
					"user_id, discount) " +
					"values (?, ?, ?, ?, ?, ?, ?, ?)";
	        int inserted2 = jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(sql2);
	                ps.setLong(1, holder.getKey().longValue());
	                ps.setInt(2, rs.getSeat().getNumber());
	                ps.setInt(3, rs.getSeat().getRow());
	                ps.setString(4, rs.getSeat().getSegment().getLabel());
	                ps.setString(5, rs.getSeat().getSegment().getHall().getLabel());
	                ps.setLong(6, rs.getSeat().getSegment().getHall().getTheater().getId());
	                ps.setLong(7, rs.getUser().getId());
	                ps.setLong(8, rs.getDiscount());
	                return ps;
	            }
	        });
		}
		
		if (discSeat != null)
			discountSeatRepository.delete(discSeat);
		
        return inserted1 != 0;
	}

}
