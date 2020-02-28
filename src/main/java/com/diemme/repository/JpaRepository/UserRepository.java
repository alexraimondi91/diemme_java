package com.diemme.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.model.User;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

}
