package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
public class ProjectionDate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	Date date;

	@Id
	@JsonFormat(pattern = "HH:mm")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	@Column(nullable = false)
	Time time;

	@Column(nullable = false)
	double price;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, optional = false)
	Projection projection;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, optional = false)
	Hall hall;
	
	public ProjectionDate() {
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}
	
	
}
