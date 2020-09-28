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
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.diemme.domain.mysql.ChatUser;
import com.diemme.domain.mysql.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "message")
public class Message extends BaseModel{	
	
	@Field("message")
	private String message;	
	@Field("file")
	private byte[] file;	
	@Field("date")
	private LocalDateTime date;
	@Field("id_user")
	private Long idUser;
	@Field("name_user")
	private String name;
	@Field("surname_user")
	private String surname;
    @DBRef
    @Field("id_chat")
	private Long idChat;
		

}
