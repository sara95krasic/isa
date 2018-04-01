package rs.ac.uns.ftn.informatika.jpa.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectionRating {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Rating rating;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Projection projection;
	
	public ProjectionRating() {
		
	}
	
	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
}
