package com.diemme.domain;

import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data @NoArgsConstructor @AllArgsConstructor
public class BaseModel {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@CreationTimestamp
	@Column(name = "insert_date", nullable = true)
	private ZonedDateTime insertDate;
	
	@UpdateTimestamp
	@Column(name = "modify_date", nullable = true)
	private ZonedDateTime modifyDate;

}