package rs.ac.uns.ftn.informatika.jpa.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@IdClass(SegmentId.class)
@Entity
public class Segment {


    @Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SegmentType segmentType;
    
    @Id
    @Column(nullable = false)
    private String label;
    @Id
    @ManyToOne(optional = false)
    private Hall hall;
    
    @OneToMany(mappedBy = "segment")
    private Set<Seat> seats;

    
    public Segment() {
    	hall = new Hall();
    	seats = new HashSet<Seat>();
    }
    
	public void setLabel(String label) {
		this.label = label;
		
	}

	public SegmentType getSegmentType() {
		return segmentType;
	}

	public void setSegmentType(SegmentType segmentType) {
		this.segmentType = segmentType;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	public String getLabel() {
		return label;
	}
    
    
	
}
