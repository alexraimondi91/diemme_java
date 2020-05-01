package com.diemme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.Designer;
import com.diemme.domain.User;

@Repository("DesignerRepository")
public interface DesignerRepository extends JpaRepository<Designer,Long> {

	Optional<Designer> findByEmail(String email);

}
