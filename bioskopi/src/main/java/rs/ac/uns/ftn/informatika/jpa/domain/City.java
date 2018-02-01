package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*
 * @Entity anotacija naznacava da je klasa perzistentni entitet. Klasa ima konstruktor bez parametara.
 * Dodatno se moze iskoristiti anotacija @Table("naziv_tabele_u_bazi") kojom se
 * specificira tacan naziv tabele u bazi, sema kojoj pripada, itd. Ako se izostavi ova anotacija, dovoljno je
 * imati anotaciju @Entity i u bazi ce se kreirati tabela sa nazivom klase.
 */
@Entity
public class City implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Svaki entitet ima svoj kljuc (surogat kljuc), dok se stategija generisanja kljuceva
	 * moze eksplicitno podesiti:
	 * - AUTO - generisanje kljuceva se oslanja na perzistencionog provajdera da izabere nacin generisanja
	 * (ako je u pitanju Hibernate, selektuje tip na osnovu dijalekta baze, za najpopularnije baze izabrace IDENTITY)
	 * - IDENTITY - inkrementalno generisanje kljuceva pri svakom novom insertu u bazu
	 * - SEQUENCE - koriste se specijalni objekti baze da se generisu id-evi
	 * - TABLE - postoji posebna tabela koja vodi racuna o kljucevima svake tabele
	 * Vise informacija na: https://en.wikibooks.org/wiki/Java_Persistence/Identity_and_Sequencing
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/*
	 * Anotacija @Column oznacava da ce neki atribut biti kolona u tabeli
	 */
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String state;

	@Column(nullable = false)
	private String country;

	@Column(nullable = false)
	private String map;

	public City() {
		
	}
	

	public City(String name, String state, String country, String map) {
		super();
		this.name = name;
		this.state = state;
		this.country = country;
		this.map = map;
	}



	public City(String name, String country) {
		super();
		this.name = name;
		this.country = country;
	}

	public String getName() {
		return this.name;
	}

	public String getState() {
		return this.state;
	}

	public String getCountry() {
		return this.country;
	}

	public String getMap() {
		return this.map;
	}

	@Override
	public String toString() {
		return getName() + "," + getState() + "," + getCountry();
	}
}
