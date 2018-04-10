package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@IdClass(value=HallId.class)
@Entity
public class Hall implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable = false)
	private String label;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	private Theater theater;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hall", cascade = CascadeType.ALL)
	private Set<Segment> segments;
	
	public Hall() {
		theater = new Theater();
		segments = new HashSet<Segment>();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}
	
	
}
