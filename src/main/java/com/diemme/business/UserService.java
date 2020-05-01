package com.diemme.business;

import java.util.Optional;

import com.diemme.domain.User;

public interface UserService {
	
	Optional<User> findByEmail (String email);

}
