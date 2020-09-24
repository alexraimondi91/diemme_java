package com.diemme.business;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.diemme.domain.mysql.Layout;
import com.diemme.domain.mysql.User;

public interface LayoutService {
	
	
	Layout getLayout(Long id) throws BusinessException;
	
	Page<Layout> getLayoutsByUserId(Long id, Integer page, Integer size) throws BusinessException;

	Layout saveLayout(Layout news) throws BusinessException;

	Page<Layout> getAllLayoutPageable(Integer page, Integer size) throws BusinessException;

	void deleteLayout(Long id) throws BusinessException;
	
	Set<User> getAllUsersLayout (Long id) throws BusinessException;

}
