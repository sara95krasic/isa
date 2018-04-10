package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Theater implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

    @Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TheaterType theaterType;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false, length = 5000)
	private String description;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "theater", cascade = CascadeType.ALL)
	private Set<Review> reviews;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "theater", cascade = CascadeType.ALL)
	private Set<Hall> halls;
	
	public Theater() {
		reviews = new HashSet<Review>();
		halls = new HashSet<Hall>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TheaterType getTheaterType() {
		return theaterType;
	}

	public void setTheaterType(TheaterType theaterType) {
		this.theaterType = theaterType;
	}

	/*public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}*/

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}


	
}
