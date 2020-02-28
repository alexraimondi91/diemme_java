package com.diemme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.diemme.model.User;
import com.diemme.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	@ResponseBody
	public List<User> getAllUsers (){
		
		return userService.getAll();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public User getUser (@PathVariable("id") Long id) {
		
		return userService.getUser(id);
	}
	
	@PostMapping("/")
	@ResponseBody
	public void addUser (@RequestBody User user) {
		
		userService.addUser(user);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public void updateUser (@RequestBody User user, @PathVariable("id") Long id) {
		
		userService.updateUser(user, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public void deleteUser (@PathVariable("id") Long id) {
		
		userService.deleteUser(id);
	}

}
