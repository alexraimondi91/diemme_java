package com.diemme.business;

import com.diemme.domain.*;
import java.util.List;


public interface IndexService {
	
	List<NewsShowcase> findAllNewsShowcases() throws BusinessException;

}
