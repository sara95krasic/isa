package rs.ac.uns.ftn.informatika.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@IdClass(SeatId.class)
@Entity
public class Seat {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	private Hall hall;
	
	@Id
	@Column
	private int number;
	
	public Seat() {
		hall = new Hall();
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
