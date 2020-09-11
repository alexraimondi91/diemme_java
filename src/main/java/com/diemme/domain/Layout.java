package com.diemme.domain;

import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@Column(name = "completed", nullable = false)
	private Boolean completed;
	@Column(name = "status", nullable = false)
	private String status;
	@Column(name = "description", nullable = true)
	@NotBlank
	private String description;
	
	@OneToMany(mappedBy="layout",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<FileLayout> flileLayout;

}
