package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.domain.Offer;


public interface OfferService {
	
	Offer save(Offer offer);
	
	List<Offer> findAll();
	
	Offer findById(Long id);
	
	Offer deleteOffer(Long id);
	
	List<Offer> findByPropId(Long propId);
	
	Offer findByCreatedByAndPropId(Long createdBy,Long propId);

}
