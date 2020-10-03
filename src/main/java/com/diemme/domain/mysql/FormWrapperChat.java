package com.diemme.domain.mysql;

import java.util.Set;

import com.diemme.domain.mongo.Chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class FormWrapperChat {
	
	private String nameProject;
	private User user;
	private String message;


}
