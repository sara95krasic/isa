package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Offer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String createdBy;
	
	@Column(nullable = false)
	private int offeredMoney; //mozda INT
	
	public Offer() {}
	
	public Offer(String cratedBy, int offeredMoney) {
		this.createdBy = cratedBy;
		this.offeredMoney = offeredMoney;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getOfferedMoney() {
		return offeredMoney;
	}

	public void setOfferedMoney(int offeredMoney) {
		this.offeredMoney = offeredMoney;
	}

}
