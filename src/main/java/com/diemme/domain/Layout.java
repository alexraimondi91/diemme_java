package com.diemme.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "layout")
@JsonIgnoreProperties(  {"handler","hibernateLazyInitializer"} )
@Data @NoArgsConstructor @AllArgsConstructor
public class Layout extends BaseModel{

	
	@Column(name = "name", nullable = false)
	@NotBlank
	private String name;
	@Column(name = "is_final", nullable = false)
	private Boolean isFinal;
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
