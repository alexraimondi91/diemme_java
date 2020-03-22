package com.diemme.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diemme.business.BusinessException;
import com.diemme.business.QuotationService;
import com.diemme.domain.QuotationShowcase;
import com.diemme.repository.QuotationShowcaseRepository;

@Service
public class QuotationServiceImpl implements QuotationService{

	@Autowired
	private QuotationShowcaseRepository quotationShowcaseRepository;
	
	@Override
	public List<QuotationShowcase> findAllQuotationShowcases() throws BusinessException{
		
		return quotationShowcaseRepository.findAll();
	}
}
