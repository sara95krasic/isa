package rs.ac.uns.ftn.informatika.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@IdClass(value=ReservationSeatId.class)
@Entity
public class ReservationSeat {
	
	@Id
	@ManyToOne(optional = false)
	private Reservation reservation;
	
	@Id
	@OneToOne(optional = false)
	private Seat seat;
	
	@ManyToOne(optional = false)
	private User user;

	@Column
	private int discount; //nije direktno vezan za DiscountSeat da bi dozvoli i neke druge vrste popusta (ono sa zlatnim, srebrnim korisnicima itd)
	
	public ReservationSeat() {
		reservation = new Reservation();
		seat = new Seat();
		user = new User();
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
}
