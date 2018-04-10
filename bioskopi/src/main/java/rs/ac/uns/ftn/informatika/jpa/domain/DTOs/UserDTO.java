package rs.ac.uns.ftn.informatika.jpa.domain.DTOs;


import org.hibernate.usertype.UserType;

import rs.ac.uns.ftn.informatika.jpa.domain.User;

public class UserDTO {
	private Long id;
	private String email;
	private String name;
	private String surname;
	private String password;
	private String city;
	private String phone;
	
	
	public UserDTO(User user){
		this.id = user.getId();
		this.email=user.getEmail();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.password = user.getPasswordHash();
		this.city = user.getCity();
		this.phone = user.getPhone();
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
