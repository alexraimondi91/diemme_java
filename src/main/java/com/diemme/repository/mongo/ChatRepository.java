package com.diemme.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.diemme.domain.mongo.Chat;
import com.diemme.domain.mongo.ChatType;

public interface ChatRepository extends MongoRepository <Chat, String>{

}
