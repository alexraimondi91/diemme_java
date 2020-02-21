package com.diemme.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.diemme.customType.UserChatID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_chat")
@Data @NoArgsConstructor @AllArgsConstructor
public class UserChat {
	
	@EmbeddedId
	private UserChatID id;
	
	@Column(name = "id", updatable = false)
	private Boolean is_sender;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_id", nullable = false, insertable = false, updatable = false)
	private Chat chat;

}
