package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Projection implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String title;

	@Column(nullable = true, length = 5000)
	private String description;

	@Column(nullable = true)
	private String genre;
	
	@Column(nullable = true)
	private String director;
	
	@Column(nullable = true)
	private int runtime;
	
	@Column(nullable = true, length = 1010379)
	private String poster; //base64 zapis
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Actor> actors;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projection", cascade = CascadeType.ALL)
	private Set<ProjectionRating> ratings;
	
	public Projection() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public Set<ProjectionRating> getRatings() {
		return ratings;
	}

	public void setRatings(Set<ProjectionRating> ratings) {
		this.ratings = ratings;
	}

	public Long getId() {
		return id;
	}

	
}
