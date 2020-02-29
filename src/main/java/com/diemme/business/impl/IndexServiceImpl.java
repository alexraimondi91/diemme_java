package com.diemme.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diemme.business.BusinessException;
import com.diemme.business.IndexService;
import com.diemme.domain.NewsShowcase;
import com.diemme.repository.NewsShowcaseRepository;

@Service
public class IndexServiceImpl implements IndexService {
	
	@Autowired
	private NewsShowcaseRepository newsShowcaseRepository;
	
	@Override
	public List<NewsShowcase> findAllNewsShowcases() throws BusinessException{
		
		return newsShowcaseRepository.findAll();
	}

}
