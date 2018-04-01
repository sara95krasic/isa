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
	
	@Column(nullable = false)
	private String director;
	
	@Column(nullable = true)
	private int runtime;
	
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

	
}
