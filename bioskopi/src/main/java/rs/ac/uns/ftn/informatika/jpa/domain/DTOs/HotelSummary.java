package rs.ac.uns.ftn.informatika.jpa.domain.DTOs;

import rs.ac.uns.ftn.informatika.jpa.domain.City;

/*
 * Interfejs koji sluzi kao wrapper za nekoliko razlicitih podataka (nalik DTO objektima).
 * U interfejsu su propisani getteri koji ce vratiti odgovarajuce podatke dobijene iz baze.
 */
public interface HotelSummary {

	City getCity();

	String getName();

	Double getAverageRating();

}
