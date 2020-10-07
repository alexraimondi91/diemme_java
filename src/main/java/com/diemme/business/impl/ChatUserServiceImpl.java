package com.diemme.business.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.ChatUserService;
import com.diemme.domain.mongo.Chat;
import com.diemme.domain.mongo.Message;
import com.diemme.domain.mysql.ChatUser;
import com.diemme.repository.mongo.ChatRepository;
import com.diemme.repository.mongo.MessageRepository;
import com.diemme.repository.mysql.ChatUserRepository;

@Service
public class ChatUserServiceImpl implements ChatUserService {

	@Autowired
	private ChatUserRepository chatUserRepository;

	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Page<ChatUser> getAllUserChat(Integer page, Integer size, Long idUser) throws BusinessException {
		return chatUserRepository.findIdChatMongoDb(PageRequest.of(page, size), idUser);
	}

	@Override
	public Set<ChatUser> getAllChatUser(String idChatMongo) throws BusinessException {
		return chatUserRepository.findUserChatMongoDb(idChatMongo);
	}

	@Override
	public Chat getChat(String idChatMongo) {
		return chatRepository.findById(idChatMongo)
				.orElseThrow(() -> new ResourceNotFoundException("FileLayout", "id", idChatMongo));
	}

	@Override
	public void deleteChat(Long idChatUser, String idChatMongo) throws BusinessException {

		chatUserRepository.deleteUserChatMongoDb(idChatMongo);
		chatRepository.deleteById(idChatMongo);
	}

	@Override
	public void saveNewChat(ChatUser chatUser, Chat chat, ChatUser chatUser2) throws BusinessException {
		chat = chatRepository.insert(chat);
		chatUser.setIdChatMongo(chat.getId());
		chatUserRepository.save(chatUser);
		chatUser2.setIdChatMongo(chat.getId());
		chatUserRepository.save(chatUser2);
	}

	@Override
	public Chat saveUpdateChat(Chat chat) throws BusinessException {

		Chat chatSave = new Chat();
		chatSave = chatRepository.save(chat);

		return chatSave;
	}
	
	@Override
	public Message saveMessage(Message message) throws BusinessException {
		
		Message messageSave = new Message();
		messageSave = messageRepository.save(message);

		return messageSave;
	}
	
	@Override
	public void saveUpdateChat(Chat chatOld, Chat chatNew) throws BusinessException {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(chatOld.getId()));
		Update update = new Update();
		update.set("messages", chatNew.getMessages());
		mongoTemplate.findAndModify(query, update, Chat.class);

	}

}
