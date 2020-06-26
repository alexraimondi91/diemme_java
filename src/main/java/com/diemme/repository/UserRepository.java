package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diemme.domain.User;

public interface UserRepository extends JpaRepository <User, Long>{
	
	User getUserByEmail(String email);

}
