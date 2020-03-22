package com.diemme.business;

import java.util.List;

import com.diemme.domain.QuotationShowcase;

public interface QuotationService {

	List<QuotationShowcase> findAllQuotationShowcases() throws BusinessException;
}
