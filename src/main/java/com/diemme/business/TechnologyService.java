package com.diemme.business;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.diemme.domain.TechnologyShowcase;

public interface TechnologyService {
	
	public List<TechnologyShowcase> getAllTecnology () throws BusinessException;
	
	public Optional<TechnologyShowcase> getTecnology (Long id) throws BusinessException;
	
	TechnologyShowcase saveTechnology (TechnologyShowcase technology) throws BusinessException;

	Page<TechnologyShowcase> getAllTecnologyPageable(Integer page, Integer size) throws BusinessException;

}
