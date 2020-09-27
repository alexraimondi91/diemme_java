package com.diemme.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.mysql.ChatUser;



@Repository("ChatRepository")
public interface ChatUserRepository extends JpaRepository <ChatUser, Long> {

}
