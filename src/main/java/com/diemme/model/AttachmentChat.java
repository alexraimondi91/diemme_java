package com.diemme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attachment_chat")
@Data @NoArgsConstructor @AllArgsConstructor
public class AttachmentChat extends BaseModel{
	
	
	@Column(name = "path", nullable = false)
	private String path;
	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToOne
    @JoinColumn(name="chat_id", nullable=false)
    private Chat chat;

}
