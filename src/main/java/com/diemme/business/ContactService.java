package com.diemme.business;

import java.util.List;
import java.util.Optional;

import com.diemme.domain.ContactShowcase;

public interface ContactService {
	
	List<ContactShowcase> findAllContactShowcases() throws BusinessException;
	
	Optional<ContactShowcase> findContactShowcase(Long id) throws BusinessException;
	
	Optional<ContactShowcase> findUltimateContac() throws BusinessException;



}
