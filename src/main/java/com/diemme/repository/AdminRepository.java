package com.diemme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.Admin;

@Repository("AdminRepository")
public interface AdminRepository extends JpaRepository<Admin,Long> {
	
 public Optional<Admin> findByEmail (String email);

}
