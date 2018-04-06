package rs.ac.uns.ftn.informatika.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@IdClass(DiscountSeatId.class)
@Entity
public class DiscountSeat {

	@Id
	@ManyToOne
	private Seat seat;
	@Id
	@ManyToOne
	private ProjectionDate projectionDate;
	
	@Column(nullable = false)
	private int discount;

	public DiscountSeat() {
		seat = new Seat();
		projectionDate = new ProjectionDate();
	}
	
	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public ProjectionDate getProjectionDate() {
		return projectionDate;
	}

	public void setProjectionDate(ProjectionDate projectionDate) {
		this.projectionDate = projectionDate;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
}
