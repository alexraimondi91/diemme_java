package com.diemme.model;

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
@Table(name = "layout")
@Data @NoArgsConstructor @AllArgsConstructor
public class Layout extends BaseModel {
	
	@Column(name = "name", nullable = false)
	@NotBlank
	private String name;

}
