package com.diemme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.Client;
import com.diemme.domain.User;

@Repository("ClientRepository")
public interface ClientRepository extends JpaRepository<Client,Long> {

	Optional<Client> findByEmail(String email);

}
