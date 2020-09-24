package com.diemme.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.mysql.ChatType;



@Repository("ChatTypeRepository")
public interface ChatTypeRepository extends JpaRepository <ChatType, Long>{

}
