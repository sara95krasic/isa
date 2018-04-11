package rs.ac.uns.ftn.informatika.jpa.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy="reservation")
	private Set<ReservationSeat> reservationSeats;
	
	@ManyToOne(optional = false)
	private ProjectionDate projectionDate;
	
	@ManyToOne(optional = false)
	private User reservedBy;
	
	
	public Reservation() {
		reservationSeats = new HashSet<ReservationSeat>();
		projectionDate = new ProjectionDate();
		reservedBy = new User();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Set<ReservationSeat> getReservationSeats() {
		return reservationSeats;
	}


	public void setReservationSeats(Set<ReservationSeat> reservationSeats) {
		this.reservationSeats = reservationSeats;
	}


	public ProjectionDate getProjectionDate() {
		return projectionDate;
	}


	public void setProjectionDate(ProjectionDate projectionDate) {
		this.projectionDate = projectionDate;
	}

	
}
