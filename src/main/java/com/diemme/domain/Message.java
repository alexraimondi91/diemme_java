package com.diemme.domain;

import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "message")
@JsonIgnoreProperties(  {"handler","hibernateLazyInitializer"} )
@Data @NoArgsConstructor @AllArgsConstructor
public class Message {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@CreationTimestamp
	@Column(name = "insertDate", nullable = true)
	private ZonedDateTime insertDate;

	@Lob
	@Column(name = "message", nullable = false)
	@NotBlank
	private String message;
	
	@Lob
	@Column(name= "content_img",length=100000, nullable = false)
	private byte[] contentImg;
	
		
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Chat> chat;
	


}
