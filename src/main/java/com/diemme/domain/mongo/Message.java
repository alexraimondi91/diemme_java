package com.diemme.domain.mongo;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.diemme.domain.mysql.Chat;
import com.diemme.domain.mysql.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "message")
public class Message {
	
	@Id
	private Long id;	
	
	private String message;	
	
	private byte[] content;	
	
	private LocalDateTime date;
		

}
