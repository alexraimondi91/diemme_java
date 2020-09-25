package com.diemme.business;

import com.diemme.domain.mysql.FileLayout;
import com.diemme.domain.mysql.Layout;

public interface FileLayoutService {
	
	FileLayout getFileLayout (Long id);
	
	FileLayout saveFileLayout(FileLayout fileLayout) throws BusinessException;
}
