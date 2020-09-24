package com.diemme.business;

import java.util.List;

import org.springframework.data.domain.Page;

import com.diemme.domain.mysql.QuotationShowcase;

public interface QuotationService {

	List<QuotationShowcase> findAllQuotationShowcases() throws BusinessException;

	Page<QuotationShowcase> getAllQuotationPageable(Integer page, Integer size) throws BusinessException;

	QuotationShowcase getQuotation(Long id) throws BusinessException;

	QuotationShowcase saveQuotation(QuotationShowcase quotation) throws BusinessException;

	void deleteQuotation(Long id) throws BusinessException;
}
