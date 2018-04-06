package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.IdClass;

@Embeddable
public class HallId implements Serializable {
	String label;
	Theater theater;

}
