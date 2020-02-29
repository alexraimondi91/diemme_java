package com.diemme.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.User;


@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

}
