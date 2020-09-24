package com.diemme.business.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.diemme.business.BusinessException;
import com.diemme.business.UserService;
import com.diemme.domain.mysql.Role;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mysql.RoleRepository;
import com.diemme.repository.mysql.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
    
	@Override
    public User findUserByEmail(String email) throws BusinessException{
        return userRepository.findByEmail(email);
    }
	
	@Override
    public User findUserByUserName(String userName) throws BusinessException{
        return userRepository.findByUserName(userName);
    }

	@Override
	@Transactional
    public User saveUser(User user) throws BusinessException{
        return userRepository.save(user);
    }

	@Override
	public Set<User> getUsersByRole(String role) throws BusinessException {
		// TODO Auto-generated method stub
		return userRepository.findUserByRole(role);
	}
	
	

}
