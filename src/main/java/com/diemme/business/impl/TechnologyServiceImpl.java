package com.diemme.business.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.TechnologyService;
import com.diemme.domain.mysql.TechnologyShowcase;
import com.diemme.repository.mysql.TechnologyShowcaseRepository;

@Service
public class TechnologyServiceImpl implements TechnologyService{
	
	@Autowired
	private TechnologyShowcaseRepository technologyShowcaseRepository;
	
	@Override
	public List<TechnologyShowcase> getAllTecnology () throws BusinessException{
		
		return technologyShowcaseRepository.findAll();
	}

	
	@Override
	public Page<TechnologyShowcase> getAllTecnologyPageable (Integer page, Integer size) throws BusinessException{
		
		return technologyShowcaseRepository.findAll(PageRequest.of(page,size));
	}
	
	@Override
	public TechnologyShowcase getTecnology (Long id) throws BusinessException{
		
		return technologyShowcaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TechnologyShowcase", "id", id));
	}
	
	@Override
	public  TechnologyShowcase saveTechnology (TechnologyShowcase technology) throws BusinessException{
		
        return technologyShowcaseRepository.save(technology);
    }
	
	@Override
	public void deleteTechnology (Long id) throws BusinessException{
		
        technologyShowcaseRepository.deleteById(id);
    }

	
	

}
