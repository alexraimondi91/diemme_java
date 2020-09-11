package com.diemme.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "file_layout")
@JsonIgnoreProperties(  {"handler","hibernateLazyInitializer"} )
@Data @NoArgsConstructor @AllArgsConstructor
public class FileLayout extends BaseModel  {
	
	@Column(name = "name", nullable = false)
	@NotBlank
	private String name;
	
	@Lob
	@Column(name= "content_img",length=1000000, nullable = false)
	private byte[] contentImg;
	@Lob
	@Column(name = "compress_img", length = 100000, nullable = true)
	private byte[] compressImg;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "layout_id", nullable = false)
	private Layout layout;

}
