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
	private Segment segment;
	
	@Id
	@Column
	private int number;
	
	@Id
	@Column
	private int row;
	
	public Seat() {
		segment = new Segment();
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	
	
}
