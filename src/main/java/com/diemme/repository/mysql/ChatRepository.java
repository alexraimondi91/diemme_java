package com.diemme.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.mysql.Chat;



@Repository("ChatRepository")
public interface ChatRepository extends JpaRepository <Chat, Long> {

}
