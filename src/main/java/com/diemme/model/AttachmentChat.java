package com.diemme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_chat")
@Data @NoArgsConstructor @AllArgsConstructor
public class AttachmentChat extends BaseModel{
	
	
	@Column(name = "path", nullable = false)
	private String path;
	@Column(name = "name", nullable = false)
	private String name;

}
