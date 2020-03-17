package com.diemme.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diemme.business.BusinessException;
import com.diemme.business.ProductService;
import com.diemme.domain.ProductShowcase;
import com.diemme.repository.ProducsShowcaseRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProducsShowcaseRepository producsShowcaseRepository;
	
	@Override
	public List<ProductShowcase> findAllProductShowcases() throws BusinessException{
		
		return producsShowcaseRepository.findAll();
	}
}
