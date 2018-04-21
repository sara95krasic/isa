package rs.ac.uns.ftn.informatika.jpa.domain.DTOs;

import rs.ac.uns.ftn.informatika.jpa.domain.ReservationSeat;

public class ReservationSeatDTO {

	private Long reservationId;
	private int seatNumber;
	private int seatRow;
	private Long userId;
	private String userNameAndSurname;
	private int disount;
	
	public ReservationSeatDTO(ReservationSeat rs) {
		this.reservationId = rs.getReservation().getId();
		this.seatNumber = rs.getSeat().getNumber();
		this.seatRow = rs.getSeat().getRow();
		this.userId = rs.getUser().getId();
		this.userNameAndSurname = rs.getUser().getName() + " " + rs.getUser().getSurname();
		this.disount = rs.getDiscount();
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public int getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserNameAndSurname() {
		return userNameAndSurname;
	}

	public void setUserNameAndSurname(String userNameAndSurname) {
		this.userNameAndSurname = userNameAndSurname;
	}

	public int getDisount() {
		return disount;
	}

	public void setDisount(int disount) {
		this.disount = disount;
	}

	
	
}
