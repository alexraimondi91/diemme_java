package com.diemme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.Productor;
import com.diemme.domain.User;

@Repository("ProductorRepository")
public interface ProductorRepository extends JpaRepository<Productor,Long>{

	Optional<Productor> findByEmail(String email);

}
