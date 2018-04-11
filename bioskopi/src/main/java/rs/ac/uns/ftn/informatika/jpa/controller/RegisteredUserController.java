package rs.ac.uns.ftn.informatika.jpa.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.domain.DiscountSeat;
import rs.ac.uns.ftn.informatika.jpa.domain.Seat;
import rs.ac.uns.ftn.informatika.jpa.domain.Segment;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDTO;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDateDTO;
import rs.ac.uns.ftn.informatika.jpa.service.ProjectionService;
import rs.ac.uns.ftn.informatika.jpa.service.SeatService;
import rs.ac.uns.ftn.informatika.jpa.service.TheaterService;
import rs.ac.uns.ftn.informatika.jpa.service.SegmentService;
import rs.ac.uns.ftn.informatika.jpa.service.HallService;

/**
 * Sadrzi funkcionalnosti namenjene registrovanim korisnicima.
 * @author jerem
 *
 */
@RestController
@RequestMapping("/registered")
public class RegisteredUserController {


	@Autowired
	private TheaterService theaterService;
	@Autowired
	private ProjectionService projectionService;
	@Autowired
	private SegmentService segmentService;
	@Autowired
	private SeatService seatService;
	
	@RequestMapping(value = "get_projection_dates_for_theater/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<Date> GetProjectionDatesForTheater(@PathVariable Long id) {
		return this.theaterService.getProjectionDatesForTheater(id, new PageRequest(0, 10));
	}
	
	
	@RequestMapping(value = "get_projections_for_theater_for_date/{id}/{date}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<ProjectionDTO> getProjectionsForTheater(@PathVariable Long id, @PathVariable Date date) {
		return this.theaterService.getProjectionsForTheaterForDate(id, date, new PageRequest(0, 10));
	}
	
	@RequestMapping(value = "get_projectiondates_for_theater_for_projection/{theaterId}/{projectionId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<ProjectionDateDTO> getProjectionDatesForTheater(@PathVariable Long theaterId, @PathVariable Long projectionId) {
		return this.theaterService.getProjectionDatesForTheaterForProjection(theaterId, projectionId, new PageRequest(0, 10));
	}
	
	
	@RequestMapping(value = "get_projections_for_theater/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<ProjectionDTO> getProjectionsForTheater(@PathVariable Long id) {
		return this.theaterService.getProjectionsForTheater(id, new PageRequest(0, 10));
	}
	
	
	@RequestMapping(value = "get_all_projections",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<ProjectionDTO> getAllProjections() {
		return this.projectionService.getAllProjections(new PageRequest(0, 10));
	}

	@RequestMapping(value = "get_all_hall_labels_for_theater/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<String> getHallLabelsForTheater(@PathVariable Long id) {
		return this.theaterService.getHallLabelsForTheater(id, new PageRequest(0, 10));
	}

	
	@RequestMapping(value = "get_discount_seats_for_theater/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DiscountSeat> getDiscountSeatsForTheater(@PathVariable Long id) {
		return this.theaterService.getDiscountSeatsForTheater(id, new PageRequest(0, 10));
	}
	
	
	@RequestMapping(value = "get_discount_seats_for_projection_date/{projection_date_id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DiscountSeat> getDiscountSeatsForProjectionDate(@PathVariable Long projection_date_id) {
		return this.theaterService.getDiscountSeatsForProjectionDate(projection_date_id, new PageRequest(0, 10));
	}

	
	@RequestMapping(value = "get_all_segments_for_hall_for_theater/{theater_id}/{hall_label}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Segment> getAllSegmentsForHallForTheater(@PathVariable Long theater_id, @PathVariable String hall_label) {
		return this.segmentService.getAllSegmentsForHallForTheater(theater_id, hall_label, new PageRequest(0, 10));
	}
	
	@RequestMapping(value = "get_segment_by_label_for_hall_for_theater/{segment_label}/{theater_id}/{hall_label}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Segment getSegmentByLabelForHallForTheater(@PathVariable String segment_label, @PathVariable Long theater_id, @PathVariable String hall_label) {
		return this.segmentService.getSegmentByLabelForHallForTheater(segment_label, theater_id, hall_label);
	}
	
	/**
	 * Vraca sve segmente u sali u kojoj se drzi taj termin projekcije (zadat po id-u).
	 * Ovo se koristi pri rezervaciji mesta sa thetaer-full.html strane.
	 * @param projection_date_id
	 * @return
	 */
	@RequestMapping(value = "get_all_segments_for_projection_date/{projection_date_id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Segment> getAllSegmentsForHallForTheater(@PathVariable Long projection_date_id) {
		return this.segmentService.getAllSegmentsForProjectionDate(projection_date_id, new PageRequest(0, 10));
	}
	
	@RequestMapping(value = "get_taken_seats/{segment_label}/{theater_id}/{hall_label}/{projection_date_id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Seat> getTakenSeats(@PathVariable String segment_label, @PathVariable Long theater_id, @PathVariable String hall_label, @PathVariable Long projection_date_id) {
		return this.seatService.getTakenSeats(segment_label, theater_id, hall_label, projection_date_id);
	}
	
}
