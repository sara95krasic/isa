package rs.ac.uns.ftn.informatika.jpa.domain.DTOs;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rs.ac.uns.ftn.informatika.jpa.domain.DiscountSeat;

public class DiscountSeatRowMapper implements RowMapper<DiscountSeat> {
	public DiscountSeat mapRow(ResultSet rs, int rowNum) throws SQLException {
/*		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1; i <= 8; i++) {
			System.out.println(i + " - " + rsmd.getColumnName(i));
		}*/

		
		
		DiscountSeat ds = new DiscountSeat();
		ds.setDiscount(rs.getInt("discount"));
		ds.getSeat().setNumber(rs.getInt("number"));
		ds.getSeat().setRow(rs.getInt("row"));
		ds.getSeat().getSegment().setLabel(rs.getString("seat_segment_label"));
		ds.getSeat().getSegment().getHall().setLabel(rs.getString("seat_segment_hall_label"));
		//ds.getSeat().getHall().getTheater().setId(rs.getLong("theater_id")); a vec trazis po id-u, ne treba ti ovo (i sad sa segmentima svejedno nece raditi)
		ds.getProjectionDate().setPrice(rs.getDouble("price"));
		ds.getProjectionDate().setDate(rs.getDate("date"));
		ds.getProjectionDate().setTime(rs.getTime("time"));
		ds.getProjectionDate().setId(rs.getLong("projection_date_id"));
		ds.getProjectionDate().getProjection().setId(rs.getLong("id"));
		ds.getProjectionDate().getProjection().setTitle(rs.getString("title"));
		ds.getProjectionDate().getProjection().setPoster(rs.getString("poster"));
		//hall i theaterid vec imas tu negde
		 
		return ds;
	}
}
