package com.diemme.domain.mongo;

import javax.persistence.Version;

import org.springframework.data.annotation.Id;

public abstract class BaseModel {
	
	@Id
	private String id;	
	@Version
	private long version;

}
