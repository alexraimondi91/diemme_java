package com.diemme.business.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
	public Optional<ContactShowcase> findUltimateContac() throws BusinessException {

		List<ContactShowcase> contactsList = contactShowcaseRepository.findAll();
		Long idMaxContact = new Long(0);

		for (Integer i = 0; i < contactsList.size(); i++) {
			if (i == 0) {

				idMaxContact = contactsList.get(i).getId();

			} else {

				if (idMaxContact < contactsList.get(i).getId()) {
					idMaxContact = contactsList.get(i).getId();
				}

			}

		}

		Optional<ContactShowcase> ultimate = contactShowcaseRepository.findById(idMaxContact);
		if (ultimate.isPresent()) {
			ultimate.get().setActive(true);
		}
		return ultimate;

	}

	@Override
	public Optional<ContactShowcase> findContactShowcase(Long id) throws BusinessException {

		return contactShowcaseRepository.findById(id);
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
