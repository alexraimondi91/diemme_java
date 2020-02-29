package com.diemme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quotation_showcases")
@JsonIgnoreProperties(  {"handler","hibernateLazyInitializer"} )
@Data @NoArgsConstructor @AllArgsConstructor
public class QuotationShowcase extends BaseModel {
	
	
	@Column(name = "name", nullable = false)
	@NotBlank
	private String name;	
	@Column(name = "description", nullable = false)
	@NotBlank
	private String description;	

}
