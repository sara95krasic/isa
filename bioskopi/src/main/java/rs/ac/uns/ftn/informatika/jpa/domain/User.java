package rs.ac.uns.ftn.informatika.jpa.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


import rs.ac.uns.ftn.informatika.jpa.domain.Role;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	@Column(nullable = false)
	private boolean verification;
	
	@Column(nullable = true)
	private int brZahteva;


	@Column(nullable = true)
	private String city;
	
	@Column(nullable = true)
	private String phone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    
//<<<<<<< HEAD
    @ManyToMany
    @JoinTable(name="friends", joinColumns=@JoinColumn(name="personId"), inverseJoinColumns=@JoinColumn(name="friendId"))
    private List<User> friends;
    
    
    @ManyToMany(targetEntity = User.class)
    @JoinTable(name="requests", joinColumns=@JoinColumn(name="receiver"))
    public List<User> receivedRequests;
    
    @ManyToMany
    @JoinTable(name="friends", joinColumns=@JoinColumn(name="friendId"), inverseJoinColumns=@JoinColumn(name="personId"))
    private List<User> friendsOf; 
    
//=======
    @Column(columnDefinition="tinyint(1) default 0")
    private boolean hasLoggedInBefore;
//>>>>>>> refs/remotes/origin/master


	public User() {
		
	}
    


	public User(String email, String passwordHash, String name, String surname, String city, String phone, Role role) {
		super();
		this.email = email;
		this.passwordHash = passwordHash;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.phone = phone;
		this.verification= false;
		this.role = role;
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

	public boolean isVerification() {
		return verification;
	}

	public void setVerification(boolean verification) {
		this.verification = verification;
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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email +
                ", passwordHash='" + passwordHash.substring(0, 10) +
                ", role=" + role +
                '}';
    }
//<<<<<<< HEAD
    
    public List<User> getFriends() {
		return friends;
	}
//=======

	public boolean isHasLoggedInBefore() {
		return hasLoggedInBefore;
	}

	public void setHasLoggedInBefore(boolean hasLoggedInBefore) {
		this.hasLoggedInBefore = hasLoggedInBefore;
	}
	
//>>>>>>> refs/remotes/origin/master


	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
    
	
    public List<User> getReceivedRequests() {
  		return receivedRequests;
  	}

  	public void setReceivedRequests(List<User> receivedRequests) {
  		this.receivedRequests = receivedRequests;
  	}

    public List<User> getFriendsOf() {
 		return friendsOf;
 	}



 	public void setFriendsOf(List<User> friendsOf) {
 		this.friendsOf = friendsOf;
 	}
 	
 	
 	public int getBrZahteva() {
		return brZahteva;
	}



	public void setBrZahteva(int brZahteva) {
		this.brZahteva = brZahteva;
	}
}
