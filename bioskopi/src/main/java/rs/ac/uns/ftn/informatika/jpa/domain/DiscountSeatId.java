package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.FetchMode;

@Embeddable
public class DiscountSeatId implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Seat seat;
	private ProjectionDate projectionDate;
}
