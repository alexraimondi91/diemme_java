package com.diemme.repository.mysql;


import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diemme.domain.mysql.FileLayout;
import com.diemme.domain.mysql.Layout;
import com.diemme.domain.mysql.StatusType;
import com.diemme.domain.mysql.User;



@Repository("LayoutRepository")
public interface LayoutRepository  extends JpaRepository<Layout, Long>{
	
	@Query(value = "SELECT l FROM Layout l JOIN l.users u WHERE u.id = :idUser")
	Page<Layout> getLayoutByUserId(Pageable pageable, @Param("idUser") Long idUser);
	
	@Query(value = "SELECT l FROM Layout l JOIN l.users u WHERE u.id = :idUser AND l.status = :statusType")
	Page<Layout> getLayoutByUserIdAndStatus(Pageable pageable, @Param("idUser") Long idUser, @Param("statusType") StatusType statusType);
	
	@Query(value = "SELECT u FROM User u JOIN u.layouts l WHERE l.id = :idLayout")
	Set<User> getUsersLayout(@Param("idLayout") Long idLayout);
	
	Page<Layout> findByStatus(Pageable pageable, StatusType status);
	

}
