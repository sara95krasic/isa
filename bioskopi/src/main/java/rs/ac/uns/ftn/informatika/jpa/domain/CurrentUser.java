package rs.ac.uns.ftn.informatika.jpa.domain;

import org.springframework.security.core.authority.AuthorityUtils;

import rs.ac.uns.ftn.informatika.jpa.domain.Role;
import rs.ac.uns.ftn.informatika.jpa.domain.User;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;

	
	private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }
    

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getRole();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                "} " + super.toString();
    }
}
