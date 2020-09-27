package com.diemme.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.diemme.domain.mongo.Chat;

public interface ChatRepository extends MongoRepository <Chat, Long>{

}
