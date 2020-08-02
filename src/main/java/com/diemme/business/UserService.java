package com.diemme.business;

import com.diemme.domain.User;

public interface UserService {
	
    User findUserByEmail(String email) throws BusinessException;
    
    User findUserByUserName(String userName)throws BusinessException;
    
    User saveUser(User user)throws BusinessException;

}
