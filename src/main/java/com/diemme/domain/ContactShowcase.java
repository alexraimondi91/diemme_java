package com.diemme.domain;

import java.time.ZonedDateTime;
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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact_showcase")
@JsonIgnoreProperties(  {"handler","hibernateLazyInitializer"} )
@Data @NoArgsConstructor @AllArgsConstructor
public class ContactShowcase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@CreationTimestamp
	@Column(name = "insertDate", nullable = true)
	private ZonedDateTime insertDate;	
	
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
	@Column(name = "who_is_name", nullable = false)
	@NotBlank
	private String whoIsName;
	@Column(name = "who_is_description", nullable = false)
	@NotBlank
	private String whoIsDescription;


}
