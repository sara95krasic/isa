package rs.ac.uns.ftn.informatika.jpa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import rs.ac.uns.ftn.informatika.jpa.domain.DiscountSeat;
import rs.ac.uns.ftn.informatika.jpa.domain.Projection;
import rs.ac.uns.ftn.informatika.jpa.domain.ProjectionDate;
import rs.ac.uns.ftn.informatika.jpa.domain.Theater;
import rs.ac.uns.ftn.informatika.jpa.service.ProjectionService;
import rs.ac.uns.ftn.informatika.jpa.service.TheaterService;

@RestController
@RequestMapping("/theater_management")
public class TheaterManagementController {

	@Autowired
	private TheaterService theaterService;
	@Autowired
	private ProjectionService projectionService;
	
	@RequestMapping(value = "update_theater/{id}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean UpdateTheater(@PathVariable Long id, @RequestBody Theater t) {
		return this.theaterService.updateTheater(id, t);
	}
	
	@RequestMapping(value = "add_new_projection",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Projection addNewProjection(@RequestBody Projection p) {
		//TODO: validiraj da je to admin pozorista ko dodaje
		return this.projectionService.addNewProjection(p);
	}
	
	@RequestMapping(value="/upload_poster/{projection_id}", 
			method=RequestMethod.POST)
    public @ResponseBody boolean uploadHandler(@RequestParam("file") MultipartFile file, @PathVariable("projection_id") Long projection_id){
        String name = "test11";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                return projectionService.setPosterImage(projection_id, bytes);
            } catch (Exception e) {
            	e.printStackTrace();
            	return false;
            }
        } else {
            return false;
        }
    }
	

	@RequestMapping(value = "add_new_projection_date/{theater_id}/{hall_label}/{projection_id}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean addNewProjectionDate(
			@RequestBody ProjectionDate pd, 
			@PathVariable Long theater_id, @PathVariable String hall_label, @PathVariable Long projection_id) {
		return this.theaterService.addNewProjectionDate(pd, theater_id, hall_label, projection_id);
	}
	
	@RequestMapping(value = "add_new_discount_seat",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean addNewDiscountSeat(@RequestBody DiscountSeat ds) {
		//TODO: validiraj da je to admin pozorista ko dodaje
		return this.theaterService.addNewDiscountSeat(ds);
	}
	
	
}
