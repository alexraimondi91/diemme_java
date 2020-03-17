package com.diemme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news_showcase")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsShowcase extends BaseModel {

	@Column(name = "name", nullable = false)
	@NotBlank
	private String name;
	
	@Lob
	@Column(name= "content_img",length=1000000, nullable = false)
	private byte[] contentImg;
	
	@Column(name = "description", nullable = false)
	@NotBlank
	private String description;

}
