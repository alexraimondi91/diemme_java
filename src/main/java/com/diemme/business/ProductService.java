package com.diemme.business;

import java.util.List;

import com.diemme.domain.ProductShowcase;


public interface ProductService {
	
	List<ProductShowcase> findAllProductShowcases() throws BusinessException;

}
