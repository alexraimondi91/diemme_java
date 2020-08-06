package com.diemme.business.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.diemme.domain.Role;
import com.diemme.business.BusinessException;
import com.diemme.business.UserService;

import com.diemme.domain.User;
import com.diemme.repository.RoleRepository;
import com.diemme.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

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

}
