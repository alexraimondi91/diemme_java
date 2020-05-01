package com.diemme.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "Productor")
public class Productor extends User{
	
	@Column(name = "p_iva", nullable = false)
	@NotBlank
	private String pIva;
	
	@Column(name = "company_name", nullable = false)
	@NotBlank
	private String companyName;

}
