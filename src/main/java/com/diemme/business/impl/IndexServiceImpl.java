package com.diemme.business.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diemme.business.BusinessException;
import com.diemme.business.IndexService;
import com.diemme.domain.mysql.NewsShowcase;
import com.diemme.repository.mysql.NewsShowcaseRepository;

@Service
public class IndexServiceImpl implements IndexService {
	
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
	



}
