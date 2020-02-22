package com.diemme.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact_showcase")
@Data @NoArgsConstructor @AllArgsConstructor
public class ContactShowcase extends BaseModel {
	
	
	@Column(name = "name", nullable = false)
	@NotBlank
	private String name;	
	@Column(name = "text", nullable = false)
	@NotBlank
	@Lob
	private String text;	
	@Column(name = "email", nullable = false)
	@Email
	@NotBlank
	private String email;	
	@Column(name = "object", nullable = false)
	@NotBlank
	private String object;	
	@Column(name = "info", nullable = false)
	@NotBlank
	private String info;
	@Column(name = "lat", nullable = true)
	@NotBlank
	private String lat;	
	@Column(name = "lon", nullable = true)
	@NotBlank
	private String lon;	

}
