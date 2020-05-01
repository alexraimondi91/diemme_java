package com.diemme.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.diemme.business.UserService;
import com.diemme.domain.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService service;
	
	

	@Override
	public	UserDetails loadUserByUsername (String email) throws UsernameNotFoundException{
	   Optional<User> user;
	try {
	   user = service.findByEmail (email);
	} catch (UsernameNotFoundException e) {
	   throw new UsernameNotFoundException ("utente non trovato");
	}
	if(user == null ) {
	   throw new UsernameNotFoundException ("utente non trovato");
	}
	return new UserDetailsImpl(user);
}

}
