package com.diemme.model;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_showcase")
@Data @NoArgsConstructor @AllArgsConstructor
public class ProductShowcase extends BaseModel {
	
	
	@Column(name = "name", nullable = false)
	@NotBlank
	private String name;	
	@Column(name = "name_file", nullable = false)
	@NotBlank
	private String nameFile;
	@Column(name = "path", nullable = false)
	@NotBlank
	private String path;	
	@Column(name = "description", nullable = false)
	@NotBlank
	private String description;	

}
