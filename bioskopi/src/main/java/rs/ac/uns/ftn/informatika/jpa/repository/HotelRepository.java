package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.City;
import rs.ac.uns.ftn.informatika.jpa.domain.Hotel;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.HotelSummary;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.RatingCount;

public interface HotelRepository extends CrudRepository<Hotel, Long> {

	Hotel findByCityAndName(City city, String name);

	/*
	 * Primer eksplicitnog pisanja upita JPQL (Java Persistence Query Language)/ HQL (Hibernate Query Language) jezikom.
	 * Kao rezultat upita vracaju se objekti tipa HotelSummary koji je interfejs ali koji u
	 * sebi ima specificirane gettere koji sluze za uvezivanje odgovarajucih tipova podataka
	 * koji se vracaju kroz upit iz baze.
	 * Dzoker znacima ?1, ?2, ?3, itd. u upit se ubacuju parametri metode u redosledu navodjenja.
	 * U primeru ?1 ce se zameniti parametrom city.
	 */
	@Query("select h.city as city, h.name as name, avg(r.rating) as averageRating "
			+ "from Hotel h left outer join h.reviews r where h.city = ?1 group by h")
	Page<HotelSummary> findByCity(City city, Pageable pageable);

	@Query("select r.rating as rating, count(r) as count "
			+ "from Review r where r.hotel = ?1 group by r.rating order by r.rating DESC")
	List<RatingCount> findRatingCounts(Hotel hotel);

}
