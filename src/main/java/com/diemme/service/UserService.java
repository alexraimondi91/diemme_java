package com.diemme.service;

import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.diemme.model.User;
import com.diemme.repository.JpaRepository.UserRepository;
import com.diemme.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public List<User> getAll () {
		
		return userRepository.findAll();
	}
	
	public User getUser( Long id) {
		
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}
	
	@Transactional(rollbackFor = Exception.class)
	public User addUser (User user) {
		
		return userRepository.save(user);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public User updateUser (User user, Long id) {
		
		if (userRepository.existsById(id)) {
			user.setId(id);
			return userRepository.save(user);
		}
		throw new ResourceNotFoundException("user", "id", id);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void deleteUser(Long id) {
		
		userRepository.deleteById(id);
	}

}
