package com.diemme.domain.mongo;

import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
public enum ChatType {
	
	CLIENT_DESIGNER,
	DESIGNER_PRODUCTOR;
	

}
