package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class ThematicProps implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = true)
	private String createdBy;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Boolean reserved;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private Date date;
	
	@Column(nullable = true, length = 1010379)
	private String picture; //base64 zapis
	
    @Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ThematicPropsType tptype;
	
	@OneToMany
	private List<Offer> offers;

	@ManyToOne
	private User reservedBy;
	
	public User getReservedBy() {
		return reservedBy;
	}

	public void setReservedBy(User user) {
		this.reservedBy = user;
	}

	public ThematicProps() 
	{}
	
	public ThematicProps(ThematicPropsType tptype,String createdBy,Boolean reserved, String name,String description,Date date,String picture) {
		this.tptype = tptype;
		this.name = name;
		this.description = description;
		this.date = date;
		this.picture = picture;	
		this.createdBy = createdBy;
		this.reserved = reserved;
	}

	public Boolean getReserved() {
		return reserved;
	}

	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public ThematicPropsType getTptype() {
		return tptype;
	}

	public void setTptype(ThematicPropsType tptype) {
		this.tptype = tptype;
	}

}
