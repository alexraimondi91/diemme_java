package com.diemme.domain.mysql;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "layout")
@EqualsAndHashCode(callSuper=true) 
@ToString(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor
public class Layout extends BaseModel{

	
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "completed", nullable = false)
	private Boolean completed;
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusType status;
	@Column(name = "description", nullable = true)
	private String description;	
		
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( 
        name = "layout_user", 
        joinColumns = @JoinColumn(
          name = "layout_id", referencedColumnName = "id", nullable = false), 
        inverseJoinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id", nullable = true)) 
	private Set<User> users;
	
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	@JoinColumn(name = "layout_id")
	private Set<FileLayout> fileLayouts;


}
