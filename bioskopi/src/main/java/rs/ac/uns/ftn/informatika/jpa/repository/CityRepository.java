package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.City;

/*
 * Primer repozitorijuma u kojem su navedene metode koje po
 * odredjenoj konstrukciji naziva prave upit u bazu.
 * Metode za pretragu pocinju sa findBy* dok u nastavku sadrze
 * nazive atributa iz modela (domain paketa), konkatenirane sa And, Or, Between, LessThan, GreaterThan, Like, itd.
 * uz dodavanje pomocnih uslova poput Containing, AllIgnoringCase, itd.
 */
/*
 * Pri startovanju Spring kontejnera trigerovace se Spring Data
 * infrastruktura koja ce kreirati binove za repozitorijume.
 * Proci se kroz metode navedene u svakom repozitorijumu i
 * pokusati da konstruise upite koji ce se pozivati pri
 * pozivu metoda.
 */

/*
 * Repository je interfejs koji dozvoljava Spring Data infrastrukturi da
 * prepozna korisnicki definisane repozitorijume (alternativa je 
 * da se sam interfejs anotira sa @Repository)
 * CrudRepository dodaje osnovne metode poput cuvanja, brisanja i pronalazenja entiteta
 * PagingAndSortingRepository nasledjuje CrudRepository i dodaje metode
 * za pristup entitetima stranicu po stranicu i njihovo soritiranje
 * JpaRepository nasledjuje PagingAndSortingRepository i dodaje JPA
 * specificne funkcionalnosti poput flush i deleteInBatch.
 * 
 * Razliciti interfejsi koji se mogu iskoristiti dozvoljavaju manipulaciju razlicitim
 * vrstama metoda koje trebaju biti podrzane - npr. repozitorijum treba
 * da bude samo readonly ili treba da ima findAll metodu koja pritom treba
 * da vraca samo deo rezultata ogranicen pomocu Pageable.
 */
public interface CityRepository extends Repository<City, Long> {

	/*
	 * Pronalazi sve objekte tipa City i vraca onoliko objekata koliko je specificirano
	 * kroz Pageable objekat.
	 * Npr. ako se prosledi objekat: new PageRequest(0, 10)
	 * vratice se nulta stranica sa prvih 10 objekata tipa City.
	 * Vise informacija na: http://docs.spring.io/autorepo/docs/spring-data-commons/1.10.0.RC1/api/org/springframework/data/domain/PageRequest.html
	 */
	Page<City> findAll(Pageable pageable);
	
	/*
	 * Ako se ne navede eksplicitni upit, Spring ce na osnovu imena
	 * metode napraviti isti.
	 * Kako name postoji kao atribut klase City upit koji
	 * ce se kreirati za ovu metodu bi bio:
	 * select c from City c where c.name = ?1
	 * uz provere da li se odgovarajuci atributi nalaze u datoj klasi.
	 */
	List<City> findByName(String name);

	/*
	 * Vraca sve objekte tipa City koji imaju u imenu prosledjen string, prosledjenu drzavu i ignorisu se mala i velika slova
	 */
	Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name,
			String country, Pageable pageable);

	/*
	 * Vraca objekat po tacnom imenu i drzavi ignorisuci mala i velika slova
	 */
	City findByNameAndCountryAllIgnoringCase(String name, String country);

}
