package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Hotel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/*
	 * Primer unidirekcione veze n:1. Klasa City nije svesna veze sa klasom Hotel.
	 * U bazi ce se u tabeli Hotel kreirati dodatna kolona koja ce sadrzati id objekata
	 * tipa City kao strani kljuc.
	 */
	@ManyToOne(optional = false)
	private City city;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String zip;

	/*
	 * Primer bidirekcione veze 1:n. Hotel sadrzi kolekciju recenzija, recenzija pripada jednom hotelu.
	 * Jedna strana veze je anotirana sa @OneToMany, dok je druga anotirana sa @ManyToOne.
	 * Dodatno je iskoriscen atribut mappedBy da se naznaci ko je vlasnik veze (hotel).
	 * U bazi ce se u tabeli Review kreirati dodatna kolona koja ce sadrzati id objekata
	 * tipa Hotel kao strani kljuc.
	 * Ako se izostavi mappedBy kreirace se medjutabela koja ce sadrzati 2 kolone -
	 * id hotela i id recenzije
	 * 
	 * Atributom fetch moze se podesavati nacin dobavljanja povezanih entiteta.
	 * Opcije su EAGER i LAZY. FetchType odlucuje da li ce se ucitati i sve veze sa odgovarajucim objektom cim se
	 * inicijalno ucita sam objekat ili nece. Ako je FetchType EAGER ucitace se sve veze sa objektom odmah, a ako je
	 * FetchType LAZY ucitace se tek pri eksplicitnom trazenju povezanih objekata (pozivanjem npr. metode getReviews).
	 * Vise informacija na: https://howtoprogramwithjava.com/hibernate-eager-vs-lazy-fetch-type/
	 * 
	 * Pored atributa fetch moze se iskoristiti i atribut cascade.
	 * CascadeType podesen na All dozvoljava da se prilikom svakog cuvanja,
	 * izmene ili brisanja Hotela cuvaju, menjaju ili brisu i recenzije. To znaci
	 * da ne moraju unapred da se cuvaju recenzije pa onda povezuju sa hotelom.
	 * orphanRemoval podesen na true ce obezbediti da se recenzije izbrisu iz baze
	 * kada se izbrisu iz kolekcije recenzija u hotelu.
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
	private Set<Review> reviews;

	public Hotel() {
		
	}

	public Hotel(City city, String name) {
		this.city = city;
		this.name = name;
	}

	public City getCity() {
		return this.city;
	}

	public String getName() {
		return this.name;
	}

	public String getAddress() {
		return this.address;
	}

	public String getZip() {
		return this.zip;
	}
}
