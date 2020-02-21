package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.diemme.model.UserChat;

@Repository("UserChatRepository")
public interface UserChatRepository extends JpaRepository <UserChat, Long>{

}
