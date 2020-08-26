package com.diemme.business.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.ContactService;
import com.diemme.domain.Contact;
import com.diemme.domain.ContactShowcase;
import com.diemme.domain.ProductShowcase;
import com.diemme.repository.ContactShowcaseRepository;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactShowcaseRepository contactShowcaseRepository;

	@Override
	public List<ContactShowcase> findAllContactShowcases() throws BusinessException {

		return contactShowcaseRepository.findAll();
	}

	@Override
	public ContactShowcase findActiveContac() throws BusinessException {

		ContactShowcase contact = contactShowcaseRepository.findActiveContact();
		return contact;
	}

	@Override
	public ContactShowcase findContactShowcase(Long id) throws BusinessException {

		return contactShowcaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact", "id", id));
	}
	
	@Override
	public ContactShowcase saveContactShowcase(ContactShowcase contact) throws BusinessException {

		return contactShowcaseRepository.save(contact);
	}
	
	@Override
	public Page<ContactShowcase> getAllContactPageable(Integer page, Integer size) throws BusinessException {
		return contactShowcaseRepository.findAll(PageRequest.of(page,size));
	}
	
	@Override
	public void deleteContactShowcase(Long id) throws BusinessException {

		contactShowcaseRepository.deleteById(id);
	}

}
