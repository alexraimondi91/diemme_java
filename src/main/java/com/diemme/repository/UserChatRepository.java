package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.Message;


@Repository("UserChatRepository")
public interface UserChatRepository extends JpaRepository <Message, Long>{

}
