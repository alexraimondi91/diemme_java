package com.diemme.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.diemme.domain.Role;
import com.diemme.domain.User;


public class UserDetailsImpl implements UserDetails {	

	private User user;
    public UserDetailsImpl(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(authority -> new SimpleGrantedAuthority(authority.getName().toString())).collect(Collectors.toList());
    }
    public Long getId() {
        return user.getId();
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    public String getEmail() {
        return user.getEmail();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public User getUserDetails() {
        return user;
    }
	@Override
	public String getUsername() {
		
		return null;
	}
}
