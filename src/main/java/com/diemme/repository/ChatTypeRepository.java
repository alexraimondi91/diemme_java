package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.ChatType;



@Repository("ChatTypeRepository")
public interface ChatTypeRepository extends JpaRepository <ChatType, Long>{

}
