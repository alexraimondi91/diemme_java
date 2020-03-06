package com.diemme.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "message")
@JsonIgnoreProperties(  {"handler","hibernateLazyInitializer"} )
@Data @NoArgsConstructor @AllArgsConstructor
public class Message extends BaseModel {	

	@Lob
	@Column(name = "message", nullable = false)
	@NotBlank
	private String message;
	
	@Lob
	@Column(name= "content_img",length=100000, nullable = false)
	private byte[] contentImg;
	
	@ManyToOne
    @JoinColumn(name="chat_id", nullable=false)
    private Chat chat;
	


}
