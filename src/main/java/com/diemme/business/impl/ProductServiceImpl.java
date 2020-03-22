package com.diemme.business.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diemme.business.BusinessException;
import com.diemme.business.ProductService;
import com.diemme.domain.ProductShowcase;
import com.diemme.repository.ProductsShowcaseRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductsShowcaseRepository producsShowcaseRepository;
	
	@Override
	public List<ProductShowcase> findAllProductShowcases() throws BusinessException{
		
		return producsShowcaseRepository.findAll();
	}
	
	@Override
	public Optional<ProductShowcase> findProductShowcase(Long id) throws BusinessException{
		
		return producsShowcaseRepository.findById(id);
	}
}
