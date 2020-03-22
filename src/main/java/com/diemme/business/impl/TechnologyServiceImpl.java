package com.diemme.business.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diemme.business.BusinessException;
import com.diemme.business.TechnologyService;
import com.diemme.domain.TechnologyShowcase;
import com.diemme.repository.ProductsShowcaseRepository;
import com.diemme.repository.TechnologyShowcaseRepository;

@Service
public class TechnologyServiceImpl implements TechnologyService{
	
	@Autowired
	private TechnologyShowcaseRepository technologyShowcaseRepository;
	
	@Override
	public List<TechnologyShowcase> getAllTecnology () throws BusinessException{
		
		return technologyShowcaseRepository.findAll();
	}
	
	@Override
	public Optional<TechnologyShowcase> getTecnology (Long id) throws BusinessException{
		
		return technologyShowcaseRepository.findById(id);
	}

}
