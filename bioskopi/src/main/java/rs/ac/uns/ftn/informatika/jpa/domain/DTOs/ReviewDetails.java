package rs.ac.uns.ftn.informatika.jpa.domain.DTOs;

import java.io.Serializable;
import java.util.Date;

import rs.ac.uns.ftn.informatika.jpa.domain.Rating;
/*
 * DTO - data transfer object
 */
public class ReviewDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private Rating rating;

	private Date date;

	public ReviewDetails() {
		
	}

	public Rating getRating() {
		return this.rating;
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
