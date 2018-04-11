package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.service.ReservationService;

@RestController
@RequestMapping("reservation")
public class SeatReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping(value = "add_new_reservation",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean addNewReservation(@RequestBody Reservation reservation) {
		return this.reservationService.makeNewReservation(reservation);
	}
	
}
