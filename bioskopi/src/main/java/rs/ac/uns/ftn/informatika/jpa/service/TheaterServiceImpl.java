package rs.ac.uns.ftn.informatika.jpa.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import rs.ac.uns.ftn.informatika.jpa.domain.DiscountSeat;
import rs.ac.uns.ftn.informatika.jpa.domain.ProjectionDate;
import rs.ac.uns.ftn.informatika.jpa.domain.Theater;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.DiscountSeatRowMapper;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDTO;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDateDTO;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.TheaterDTO;
import rs.ac.uns.ftn.informatika.jpa.repository.HallRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.TheaterRepository;

@Service
public class TheaterServiceImpl implements TheaterService {

	
	@Autowired
	private TheaterRepository theaterRepository;
	@Autowired
	private HallRepository hallRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	@Override
	public Page<TheaterDTO> getMovieTheaters(String name, Pageable pageable) {
		Assert.notNull(name, "Name ne sme biti null");
		return this.theaterRepository.getMovieThearersByName(name, pageable);
	}

	@Override
	public Page<TheaterDTO> getAllMovieTheaters(Pageable pageable) {
		return this.theaterRepository.getAllMovieThearers(pageable);
	}
	

	@Override
	public Page<TheaterDTO> getPlayTheaters(String name, Pageable pageable) {
		Assert.notNull(name, "Name ne sme biti null");
		return this.theaterRepository.getPlayThearersByName(name, pageable);
	}

	@Override
	public Page<TheaterDTO> getAllPlayTheaters(Pageable pageable) {
		return this.theaterRepository.getAllPlayThearers(pageable);
	}

	@Override
	public TheaterDTO getTheaterById(Long id) {
		return this.theaterRepository.getTheaterById(id);
	}
	
	@Override
	public boolean updateTheater(Long id, Theater t) {
		int ret = this.theaterRepository.updateTheaterById(id, t.getName(), t.getAddress(), t.getDescription(), t.getTheaterType());
		return ret != 0;
	}
	
	@Override
	public Page<Date> getProjectionDatesForTheater(Long id, Pageable pageable) {
		return this.theaterRepository.getAllProjectionDatesForTheater(id, pageable);
	}

	@Override
	public Page<ProjectionDTO> getProjectionsForTheaterForDate(Long id, Date date, Pageable pageable) {
		return this.theaterRepository.getAllProjectionsForTheaterForDate(id, date, pageable);
	}

	@Override
	public Page<ProjectionDateDTO> getProjectionDatesForTheaterForProjection(Long theaterId, Long projectionId, Pageable pageable) {
		return this.theaterRepository.getAllProjectionDatesForTheaterForProjection(theaterId, projectionId, pageable);
	}

	@Override
	public Page<ProjectionDTO> getProjectionsForTheater(Long id, Pageable pageable) {
		return this.theaterRepository.getAllProjectionsForTheater(id, pageable);

	}

	@Override
	public Page<String> getHallLabelsForTheater(Long id, Pageable pageable) {
		return this.theaterRepository.getAllHallLabelsForTheater(id, pageable);
	}

	@Override
	public boolean addNewProjectionDate(ProjectionDate pd, Long theater_id, String hall_label, Long projection_id) {
		final String sql = "insert into projection_date(projection_id, hall_label, hall_theater_id, time, date, price) " +
				"values (?,?,?,?,?,?)";
        int inserted = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setLong(1, projection_id);
                ps.setString(2, hall_label);
                ps.setLong(3, theater_id);
                ps.setTime(4, pd.getTime());
                ps.setDate(5, pd.getDate());
                ps.setDouble(6, pd.getPrice());
                return ps;
            }
        });
        return inserted != 0;
	}

	@Override
	public List<DiscountSeat> getDiscountSeatsForTheater(Long id, Pageable pageable) {
		
		String sql = "select ds.discount, ds.projection_date_id, ds.seat_hall_label, t.name, s.number, p.id, pd.price, pd.date, pd.time, p.title, p.poster " +
					"from discount_seat ds, seat s, hall h, theater t, projection_date pd, projection p " +
					"where t.id = ? and " +
						"ds.seat_number = s.number and ds.seat_hall_label = s.hall_label and ds.seat_hall_theater_id = s.hall_theater_id " +
					    "and " +
					    "s.hall_label = h.label and s.hall_theater_id = h.theater_id " +
					    "and " +
					    "h.theater_id = t.id " +
					    "and " +
					    "pd.id = ds.projection_date_id " +
					    "and " +
					    "pd.projection_id = p.id " +
					"order by ds.seat_number;";
		List<DiscountSeat> dss = jdbcTemplate.query(sql, new Object[] {id}, new DiscountSeatRowMapper());
		
		return dss;
	}

	@Override
	public boolean addNewDiscountSeat(DiscountSeat ds) {
		final String sql = "insert into discount_seat (projection_date_id, seat_hall_theater_id, seat_hall_label, seat_number, discount) " +
				"values (?,?,?,?,?)";
        int inserted = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setLong(1, ds.getProjectionDate().getId());
                ps.setLong(2, ds.getSeat().getHall().getTheater().getId());
                ps.setString(3, ds.getSeat().getHall().getLabel());
                ps.setInt(4, ds.getSeat().getNumber());
                ps.setInt(5, ds.getDiscount());
                return ps;
            }
        });
        return inserted != 0;
	}

	@Override
	public List<DiscountSeat> getDiscountSeatsForProjectionDate(Long projection_date_id, PageRequest pageRequest) {
		
		String sql = "select ds.discount, ds.projection_date_id, ds.seat_hall_label, t.name, s.number, p.id, pd.price, pd.date, pd.time, p.title, p.poster " +
					"from discount_seat ds, seat s, hall h, theater t, projection_date pd, projection p " +
					"where pd.id = ? and " +
						"ds.seat_number = s.number and ds.seat_hall_label = s.hall_label and ds.seat_hall_theater_id = s.hall_theater_id " +
					    "and " +
					    "s.hall_label = h.label and s.hall_theater_id = h.theater_id " +
					    "and " +
					    "h.theater_id = t.id " +
					    "and " +
					    "pd.id = ds.projection_date_id " +
					    "and " +
					    "pd.projection_id = p.id " +
					"order by ds.seat_number;";
		List<DiscountSeat> dss = jdbcTemplate.query(sql, new Object[] {projection_date_id}, new DiscountSeatRowMapper());
		
		return dss;
	}
}
