package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.util.Assert;

import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ReviewDetails;

@Entity
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/*
	 * Druga strana bidirekcione veze 1:n
	 */
	@ManyToOne(optional = false)
	private Hotel hotel;

	/*
	 * Kolona moze imati ime koje se razlikuje od naziva atributa.
	 */
	@Column(nullable = false, name = "idx")
	private int index;

	@Column(nullable = false)
	/*
	 * Nacin na koji ce se enumeracija cuvati u bazi (kao broj ili string)
	 */
	@Enumerated(EnumType.ORDINAL)
	private Rating rating;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date checkInDate;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TripType tripType;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false, length = 5000)
	private String details;

	public Review() {
		
	}

	public Review(Hotel hotel, int index, ReviewDetails details) {
		Assert.notNull(hotel, "Hotel ne sme biti null");
		Assert.notNull(details, "Detalji ne smeju biti null");
		this.hotel = hotel;
		this.index = index;
		this.rating = details.getRating();
		this.checkInDate = details.getCheckInDate();
		this.tripType = details.getTripType();
		this.title = details.getTitle();
		this.details = details.getDetails();
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	public int getIndex() {
		return this.index;
	}

	public Rating getRating() {
		return this.rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Date getCheckInDate() {
		return this.checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public TripType getTripType() {
		return this.tripType;
	}

	public void setTripType(TripType tripType) {
		this.tripType = tripType;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
