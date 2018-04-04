package rs.ac.uns.ftn.informatika.jpa.domain.DTOs;

import java.sql.Date;
import java.sql.Time;

public interface ProjectionDateDTO {

	Date getDate();
	
	Time getTime();
	
	double getPrice();
	
	String getHallLabel();
}
