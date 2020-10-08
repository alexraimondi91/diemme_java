package com.diemme.business.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.LayoutService;
import com.diemme.domain.mysql.Layout;
import com.diemme.domain.mysql.StatusType;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mysql.LayoutRepository;

@Service
public class layoutServiceImpl implements LayoutService{
	
	@Autowired
	private LayoutRepository layoutRepository;


	@Override
	public Layout getLayout(Long id) throws BusinessException {		
		return layoutRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Layout", "id", id));
	}

	@Override
	public Layout saveLayout(Layout layout) throws BusinessException {		
		return layoutRepository.save(layout);
	}

	@Override
	public Page<Layout> getAllLayoutPageable(Integer page, Integer size) throws BusinessException {		
		return layoutRepository.findAll(PageRequest.of(page,size));
	}

	@Override
	public void deleteLayout(Long id) throws BusinessException {
		layoutRepository.deleteById(id);		
	}

	@Override
	public Page<Layout> getLayoutsByUserId(Long id, Integer page, Integer size) throws BusinessException {	
		Pageable pageable = PageRequest.of(page,size);
		Page<Layout> pageLayout = layoutRepository.getLayoutByUserId(pageable, id);
		return pageLayout;
	}

	@Override
	public Set<User> getAllUsersLayout(Long id) throws BusinessException {
		return layoutRepository.getUsersLayout(id);
	}
	
	@Override
	public Page<Layout> getLayoutsByStatus(StatusType status, Integer page, Integer size) throws BusinessException {	
		Pageable pageable = PageRequest.of(page,size);
		Page<Layout> pageLayout = layoutRepository.findByStatus(pageable, status);
		return pageLayout;
	}

	

}
