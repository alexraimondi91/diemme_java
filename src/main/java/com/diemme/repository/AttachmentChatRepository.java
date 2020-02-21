package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.diemme.model.AttachmentChat;

@Repository("AttachmentChatRepository")
public interface AttachmentChatRepository extends JpaRepository<AttachmentChat, Long> {

}
