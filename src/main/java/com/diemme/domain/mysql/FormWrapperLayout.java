package com.diemme.domain.mysql;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor

public class FormWrapperLayout {
	
	private Layout layout;
	private Set<User> userClient;

}
