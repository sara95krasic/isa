package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SeatId implements Serializable{
	private Segment segment;
	private int number;
	private int row;
}
