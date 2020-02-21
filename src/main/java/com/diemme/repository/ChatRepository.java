package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.model.Chat;

@Repository("ChatRepository")
public interface ChatRepository extends JpaRepository <Chat, Long> {

}
