package com.diemme.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.model.ChatType;

@Repository("ChatTypeRepository")
public interface ChatTypeRepository extends JpaRepository <ChatType, Long>{

}
