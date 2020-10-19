package com.diemme.domain.mysql;

import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "contact_showcase")
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper=true) 
@ToString(callSuper = true)
public class ContactShowcase extends BaseModel{
	
	
	@Column(name = "info", nullable = false)
	@NotBlank
	private String info;
	@Column(name = "lat", nullable = true)
	@NotBlank
	private String lat;	
	@Column(name = "lon", nullable = true)
	@NotBlank
	private String lon;	
	@Column(name = "active", nullable = false)
	private Boolean active;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;


}
