package com.diemme.config;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.diemme.domain.User;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;

	Set<GrantedAuthority> authorities = null;

	public UserDetailsImpl(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	 @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return user.getRoles().stream().map(authority -> new SimpleGrantedAuthority(authority.getName().toString())).collect(Collectors.toList());
	    }


	public String getPassword() {
		return user.getPassword();
	}

	public String getEmail() {
		return user.getEmail();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
	
	 public User getUserDetails() {
	        return user;
	    }

	@Override
	public String getUsername() {
		return user.getEmail();
	}
}
