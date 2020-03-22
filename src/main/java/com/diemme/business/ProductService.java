package com.diemme.business;

import java.util.List;
import java.util.Optional;

import com.diemme.domain.ProductShowcase;


public interface ProductService {
	
	List<ProductShowcase> findAllProductShowcases() throws BusinessException;
	
	Optional<ProductShowcase> findProductShowcase(Long id) throws BusinessException;

}
