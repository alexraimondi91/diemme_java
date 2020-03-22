package com.diemme.business;

import java.util.List;
import java.util.Optional;

import com.diemme.domain.TechnologyShowcase;

public interface TechnologyService {
	
	public List<TechnologyShowcase> getAllTecnology () throws BusinessException;
	
	public Optional<TechnologyShowcase> getTecnology (Long id) throws BusinessException;

}
