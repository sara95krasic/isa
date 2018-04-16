package rs.ac.uns.ftn.informatika.jpa.domain.DTOs;

import java.util.Date;

import rs.ac.uns.ftn.informatika.jpa.domain.ThematicProps;
import rs.ac.uns.ftn.informatika.jpa.domain.ThematicPropsType;

public class ThematicPropsDTO {
	

	private Long id;
	private ThematicPropsType tptype;
	private String name;
	private String description;
	private Date date;
	private String picture;
	private String createdBy;
	private Boolean reserved;
	private Long reservedById;
	
	public ThematicPropsDTO(ThematicProps thematicProps) {
		this.id = thematicProps.getId();
		this.tptype = thematicProps.getTptype();
		this.name = thematicProps.getName();
		this.description = thematicProps.getDescription();
		this.date = thematicProps.getDate();
		this.picture = thematicProps.getPicture();	
		this.createdBy = thematicProps.getCreatedBy();
		this.reserved = thematicProps.getReserved();
		this.reservedById = thematicProps.getReservedBy().getId();
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Boolean getReserved() {
		return reserved;
	}

	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}

	public Long getReservedById() {
		return reservedById;
	}

	public void setReservedById(Long reservedById) {
		this.reservedById = reservedById;
	}
	
	}
