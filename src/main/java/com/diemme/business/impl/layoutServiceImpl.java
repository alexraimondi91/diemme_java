package com.diemme.business.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.diemme.business.BusinessException;
import com.diemme.business.LayoutService;
import com.diemme.domain.Layout;

@Service
public class layoutServiceImpl implements LayoutService{

	@Override
	public List<Layout> findAllLayout() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Layout> findNewsLayout(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Layout getLayout(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Layout saveLayout(Layout news) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Layout> getAllLayoutPageable(Integer page, Integer size) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLayout(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

}
