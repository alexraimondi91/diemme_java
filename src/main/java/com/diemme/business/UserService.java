package com.diemme.business;

import java.util.Set;

import com.diemme.domain.mysql.User;

public interface UserService {
	
    User findUserByEmail(String email) throws BusinessException;
    
    User findUserByUserName(String userName)throws BusinessException;
    
    User saveUser(User user)throws BusinessException;
    
    Set<User> getUsersByRole (String role)throws BusinessException;

}
