package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.domain.City;
import rs.ac.uns.ftn.informatika.jpa.domain.Hotel;
import rs.ac.uns.ftn.informatika.jpa.domain.Review;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.HotelSummary;
import rs.ac.uns.ftn.informatika.jpa.service.CityService;
import rs.ac.uns.ftn.informatika.jpa.service.HotelService;

@RestController
public class SampleController {

	@Autowired
	private CityService cityService;
	
	@Autowired
	private HotelService hotelService;

	@RequestMapping(value = "/search/city/{name}/{country}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public City getCityByName(@PathVariable String name, @PathVariable String country) {
		return this.cityService.getCity(name, country);
	}
	
	@RequestMapping(value = "/search/city/{criteria}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<City> getCityByCriteria(@PathVariable String criteria) {
		return this.cityService.findCities(criteria, new PageRequest(0, 10));
	}
	
	@RequestMapping(value = "/search/city/{cityname}/{country}/summary",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<HotelSummary> getHotelsByCity(@PathVariable String cityname, @PathVariable String country) {
		City city = this.cityService.getCity(cityname, country);
		return this.cityService.getHotels(city, new PageRequest(0, 10));
	}
	
	
	@RequestMapping(value = "/search/reviews/{cityname}/{country}/{name}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<Review> getReview(@PathVariable String cityname, @PathVariable String country, @PathVariable String name) {
		Hotel hotel = hotelService.getHotel(this.cityService.getCity(cityname, country), name);
		return this.hotelService.getReviews(hotel, new PageRequest(0, 10));
	}

}
