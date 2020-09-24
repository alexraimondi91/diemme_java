package com.diemme.business.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.NewsService;
import com.diemme.domain.mysql.NewsShowcase;
import com.diemme.repository.mysql.NewsShowcaseRepository;

@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsShowcaseRepository newsShowcaseRepository;
	
	@Override
	public List<NewsShowcase> findAllNewsShowcases() throws BusinessException{
		
		return newsShowcaseRepository.findAll();
	}
	
	@Override
	public Optional<NewsShowcase> findNewsShowcase(Long id) throws BusinessException{
		
		return newsShowcaseRepository.findById(id);
	}
	
	@Override
	public NewsShowcase getNews (Long id) throws BusinessException{
		
		return newsShowcaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NewsShowcase", "id", id));
	}
	

	@Override
	public NewsShowcase saveNews(NewsShowcase news) throws BusinessException {
		return newsShowcaseRepository.save(news);
	}

	@Override
	public Page<NewsShowcase> getAllNewsPageable(Integer page, Integer size) throws BusinessException {
		return newsShowcaseRepository.findAll(PageRequest.of(page,size));
	}

	@Override
	public void deleteNews(Long id) throws BusinessException {
		newsShowcaseRepository.deleteById(id);
	}

}
