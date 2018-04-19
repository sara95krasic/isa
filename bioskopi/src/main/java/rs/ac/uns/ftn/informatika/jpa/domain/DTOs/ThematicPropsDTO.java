package rs.ac.uns.ftn.informatika.jpa.domain.DTOs;

import rs.ac.uns.ftn.informatika.jpa.domain.ThematicProps;
import rs.ac.uns.ftn.informatika.jpa.domain.ThematicPropsType;


public class ThematicPropsDTO {
	
	private Long id;
	private ThematicPropsType tptype;
	private String name;
	private String description;
	private String date;
	private String picture;
	private Long createdBy;
	private String reserved;
	private Long theaterId;
	private Boolean approved;
	
	public ThematicPropsDTO(ThematicProps thematicProps) {
		this.id = thematicProps.getId();
		this.tptype = thematicProps.getTptype();
		this.name = thematicProps.getName();
		this.description = thematicProps.getDescription();
		this.date = thematicProps.getDate();
		this.picture = thematicProps.getPicture();	
		this.createdBy = thematicProps.getCreatedBy();
		this.reserved = thematicProps.getReserved();
		this.theaterId = thematicProps.getTheaterId();
		this.approved = thematicProps.getApproved();
	}

	
	public Boolean getApproved() {
		return approved;
	}


	public void setApproved(Boolean approved) {
		this.approved = approved;
	}


	public Long getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ThematicPropsType getTptype() {
		return tptype;
	}

	public void setTptype(ThematicPropsType tptype) {
		this.tptype = tptype;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	
	
}
