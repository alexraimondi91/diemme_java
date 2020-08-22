package com.diemme.business.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.ProductService;
import com.diemme.domain.ProductShowcase;
import com.diemme.domain.TechnologyShowcase;
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
	
	@Override
	public ProductShowcase getProduct (Long id) throws BusinessException{
		
		return producsShowcaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("producsShowcase", "id", id));
	}
	

	@Override
	public ProductShowcase saveProduct(ProductShowcase product) throws BusinessException {
		return producsShowcaseRepository.save(product);
	}

	@Override
	public Page<ProductShowcase> getAllProductPageable(Integer page, Integer size) throws BusinessException {
		return producsShowcaseRepository.findAll(PageRequest.of(page,size));
	}

	@Override
	public void deleteProduct(Long id) throws BusinessException {
		producsShowcaseRepository.deleteById(id);
	}
}
