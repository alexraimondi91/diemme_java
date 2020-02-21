package com.diemme.model;


import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat")
@Data @NoArgsConstructor @AllArgsConstructor
public class Chat extends BaseModel {
	
	@Column(name = "type", nullable = false)
	@NotBlank
	private String type;
	@Lob
	@Column(name = "message", nullable = false)
	@NotBlank
	private String message;
	@CreationTimestamp
	@Column(name = "insertDate")
	private ZonedDateTime insertDate;
	
	
	@OneToMany(fetch = FetchType.LAZY,  mappedBy="chat")
	@JoinColumn(name = "attachment_chat_id", nullable = true)
	Set<AttachmentChat> attachmentChat;
	
}
