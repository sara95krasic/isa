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

	@ManyToOne(optional = false)
	private MovieTheater movieTheater;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Rating rating;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;

	public Review() {
		
	}

	public Review(MovieTheater movieTheater, ReviewDetails details) {
		Assert.notNull(movieTheater, "Hotel ne sme biti null");
		Assert.notNull(details, "Detalji ne smeju biti null");
		this.movieTheater = movieTheater;
		this.rating = details.getRating();
		this.date = details.getDate();
	}

	public MovieTheater getMovieTheater() {
		return movieTheater;
	}

	public void setMovieTheater(MovieTheater movieTheater) {
		this.movieTheater = movieTheater;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


}
