package com.diemme.domain.mongo;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Version;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "chat")
public class Chat extends BaseModel{	
	
    @DBRef
    @Field("chat_type")
	private ChatType chatType;
    @Field("messages")
    private Set<Message> messages;

}
