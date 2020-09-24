package com.diemme.business;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.diemme.domain.mysql.ContactShowcase;

public interface ContactService {
	
	List<ContactShowcase> findAllContactShowcases() throws BusinessException;
	
	ContactShowcase findContactShowcase(Long id) throws BusinessException;
	
	ContactShowcase findActiveContac() throws BusinessException;

	void deleteContactShowcase(Long id) throws BusinessException;

	ContactShowcase saveContactShowcase(ContactShowcase contact) throws BusinessException;

	Page<ContactShowcase> getAllContactPageable(Integer page, Integer size) throws BusinessException;





}
