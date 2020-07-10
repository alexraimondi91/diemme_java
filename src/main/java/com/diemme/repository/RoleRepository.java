package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.Role;
import com.diemme.domain.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
    Role findByRole(String role);


}
