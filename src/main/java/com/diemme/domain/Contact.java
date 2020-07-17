package com.diemme.domain;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Contact {
	
	private String name;	
	private String text;	
	private String email;	
	private String object;	

}
