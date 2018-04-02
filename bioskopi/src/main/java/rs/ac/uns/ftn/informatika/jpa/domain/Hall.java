package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@IdClass(value=HallId.class)
@Entity
public class Hall implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable = false)
	private String label;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	private Theater theater;
}
