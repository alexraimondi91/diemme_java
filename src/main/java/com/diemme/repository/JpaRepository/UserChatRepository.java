package com.diemme.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.diemme.model.Message;

@Repository("UserChatRepository")
public interface UserChatRepository extends JpaRepository <Message, Long>{

}
