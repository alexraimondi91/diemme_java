package com.diemme.business;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.diemme.domain.mysql.Layout;
import com.diemme.domain.mysql.StatusType;
import com.diemme.domain.mysql.User;
import com.diemme.wrapperForm.FormWrapperLayout;

public interface LayoutService {
	
	
	Layout getLayout(Long id) throws BusinessException;
	
	Page<Layout> getLayoutsByUserId(Long id, Integer page, Integer size) throws BusinessException;

	Page<Layout> getAllLayoutPageable(Integer page, Integer size) throws BusinessException;

	void deleteLayout(Long id) throws BusinessException;
	
	Set<User> getAllUsersLayout (Long id) throws BusinessException;

	Page<Layout> getLayoutsByStatus(StatusType status, Integer page, Integer size) throws BusinessException;


	Page<Layout> getMyLayoutsByStatus(Long id, StatusType status, Integer page, Integer size) throws BusinessException;

	void createLayout(FormWrapperLayout layoutWrapper, List<MultipartFile> contentImg, User userAuth)
			throws BusinessException;

	Layout updateLayout(Long id, Layout layout, List<MultipartFile> contentImg, User userAuth) throws BusinessException;

	Layout updateProductorLayout(Long id, Layout layout, User userAuth) throws BusinessException;

	Layout updateClientLayout(Long id, Layout layout, User userAuth) throws BusinessException;

}
