package com.diemme.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_showcase")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductShowcase extends BaseModel{


	@Column(name = "name", nullable = false)
	@NotBlank
	private String name;
	@Lob
	@Column(name= "content_img", length=100000, nullable = false)
	private byte[] contentImg;
	@Lob
	@Column(name = "compress_img", length = 100000, nullable = true)
	private byte[] compressImg;
	@Column(name = "description", nullable = false)
	@NotBlank
	private String description;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

}
