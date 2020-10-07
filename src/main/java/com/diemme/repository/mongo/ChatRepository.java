package com.diemme.repository.mongo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.diemme.domain.mongo.Chat;
import com.diemme.domain.mongo.ChatType;

public interface ChatRepository extends MongoRepository <Chat, String>{
	
	/*
	 * @Transactional
	 * 
	 * @Modifying
	 * 
	 * @Query(value = "UPDATE FROM Chat c WHERE c.id = :idChatMongo") public void
	 * updateChatMongoDb(@Param("idChatMongo") String idChatMongo);
	 */

}
