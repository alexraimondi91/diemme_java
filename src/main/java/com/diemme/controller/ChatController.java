package com.diemme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.diemme.model.Chat;
import com.diemme.service.ChatService;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@GetMapping("/")
	@ResponseBody
	public List<Chat> getAll (){
		
		return chatService.getAll();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Chat getChat (@PathVariable("id") Long id) {
		
		return chatService.getChat(id);
	}
	
	@PostMapping("/")
	@ResponseBody
	public void addChat (@RequestBody Chat chat) {
		
		chatService.addChat(chat);
	}
	
	@PostMapping("/{id}")
	@ResponseBody
	public Chat updateChat (@RequestBody Chat chat, @PathVariable("id") Long id) {
		
		return chatService.updateChat(chat, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public void deleteChat (@RequestBody Chat chat) {
		
		chatService.deleteChat(chat);
	}

}
