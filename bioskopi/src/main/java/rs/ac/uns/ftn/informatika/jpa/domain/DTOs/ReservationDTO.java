package rs.ac.uns.ftn.informatika.jpa.domain.DTOs;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.ReservationSeat;

public class ReservationDTO {
	
	private Long id;
	private Long projectionDateId;
	private Date projectionDateDate;
	private Time projectionDateTime;
	private double projectionDatePrice;
	private Long projectionId;
	private String projectionTitle;
	private List<ReservationSeatDTO> reservationSeats;
	private String hallLabel;
	private Long theaterId;
	private String theaterName;
	private String segmentLabel;
	
	public ReservationDTO() {
		reservationSeats = new ArrayList<ReservationSeatDTO>();
	}
	
	public ReservationDTO(Reservation reservation) {
		this.id = reservation.getId();
		this.projectionDateId = reservation.getProjectionDate().getId();
		this.projectionDateDate = reservation.getProjectionDate().getDate();
		this.projectionDateTime = reservation.getProjectionDate().getTime();
		this.projectionDatePrice = reservation.getProjectionDate().getPrice();
		this.projectionId = reservation.getProjectionDate().getProjection().getId();
		this.projectionTitle = reservation.getProjectionDate().getProjection().getTitle();
		this.reservationSeats = new ArrayList<ReservationSeatDTO>();
		this.hallLabel = reservation.getProjectionDate().getHall().getLabel();
		this.theaterId = reservation.getProjectionDate().getHall().getTheater().getId();
		this.theaterName = reservation.getProjectionDate().getHall().getTheater().getName();
		for (ReservationSeat rs : reservation.getReservationSeats()) {
			this.segmentLabel = rs.getSeat().getSegment().getLabel(); //ovo je isto za svako sediste
			reservationSeats.add(new ReservationSeatDTO(rs));
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectionDateId() {
		return projectionDateId;
	}

	public void setProjectionDateId(Long projectionDateId) {
		this.projectionDateId = projectionDateId;
	}

	public Date getProjectionDateDate() {
		return projectionDateDate;
	}

	public void setProjectionDateDate(Date projectionDateDate) {
		this.projectionDateDate = projectionDateDate;
	}

	public Time getProjectionDateTime() {
		return projectionDateTime;
	}

	public void setProjectionDateTime(Time projectionDateTime) {
		this.projectionDateTime = projectionDateTime;
	}

	public double getProjectionDatePrice() {
		return projectionDatePrice;
	}

	public void setProjectionDatePrice(double projectionDatePrice) {
		this.projectionDatePrice = projectionDatePrice;
	}

	public Long getProjectionId() {
		return projectionId;
	}

	public void setProjectionId(Long projectionId) {
		this.projectionId = projectionId;
	}

	public String getProjectionTitle() {
		return projectionTitle;
	}

	public void setProjectionTitle(String projectionTitle) {
		this.projectionTitle = projectionTitle;
	}

	public List<ReservationSeatDTO> getReservationSeats() {
		return reservationSeats;
	}

	public void setReservationSeats(List<ReservationSeatDTO> reservationSeats) {
		this.reservationSeats = reservationSeats;
	}

	public String getHallLabel() {
		return hallLabel;
	}

	public void setHallLabel(String hallLabel) {
		this.hallLabel = hallLabel;
	}

	public Long getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getSegmentLabel() {
		return segmentLabel;
	}

	public void setSegmentLabel(String segmentLabel) {
		this.segmentLabel = segmentLabel;
	}
	
	
}
