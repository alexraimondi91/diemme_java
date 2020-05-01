package com.diemme.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.diemme.domain.Admin;
import com.diemme.domain.Client;
import com.diemme.domain.Designer;
import com.diemme.domain.Productor;
import com.diemme.domain.Role;
import com.diemme.domain.User;
import com.diemme.repository.AdminRepository;
import com.diemme.repository.ClientRepository;
import com.diemme.repository.DesignerRepository;
import com.diemme.repository.ProductorRepository;

public class UserDetailsImpl implements UserDetails {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ProductorRepository productorRepository;
	
	@Autowired
	private DesignerRepository designerRepository;

	private static final String ROLE_PREFIX = "ROLE_";

	private static final SimpleGrantedAuthority ROLE_ADMIN = new SimpleGrantedAuthority(ROLE_PREFIX + "admin");

	private static final SimpleGrantedAuthority ROLE_CLIENT = new SimpleGrantedAuthority(ROLE_PREFIX + "client");

	private static final SimpleGrantedAuthority ROLE_DESIGNER = new SimpleGrantedAuthority(ROLE_PREFIX + "designer");

	private static final SimpleGrantedAuthority ROLE_PRODUCTOR = new SimpleGrantedAuthority(ROLE_PREFIX + "productor");

	public Optional<User> user;

	public UserDetailsImpl(Optional<User> user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
		for (Role role : user.get().getRoles()) {

			result.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getName()));
		}

		if (adminRepository.findById(user.get().getId()).isPresent()) {
			result.add(ROLE_ADMIN);
		}

		if (clientRepository.findById(user.get().getId()).isPresent()) {
			result.add(ROLE_CLIENT);
		}

		if (designerRepository.findById(user.get().getId()).isPresent()) {
			result.add(ROLE_DESIGNER);
		}

		if (productorRepository.findById(user.get().getId()).isPresent()) {
			result.add(ROLE_PRODUCTOR);
		}
		return result;
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
	public String getPassword() {
		return user.get().getPassword();
	}

	@Override
	public String getUsername() {
		return user.get().getEmail();
	}

	@Override
	public boolean isEnabled() {
		return user.get().getActive();
	}

}
