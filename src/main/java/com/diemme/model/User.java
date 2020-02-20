package com.diemme.model;

import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	@Column(name = "name", nullable = false)
	@NotBlank
	private String name;
	@Column(name = "surname", nullable = false)
	@NotBlank
	private String surname;
	@Column(name = "email", nullable = false, unique = true)
	@Email 
    @NotBlank
	private String email;
	@Column(name = "password", nullable = false)
	@NotBlank
	private String password;
	@Column(name = "fiscal_code", nullable = false)
	@NotBlank
	private String fiscalCode;
	@Column(name = "address", nullable = false)
	@NotBlank
	private String address;
	@Column(name = "country", nullable = false)
	@NotBlank
	private String country;
	@CreationTimestamp
	private ZonedDateTime insertDate;
	@Column(name = "active", nullable = false)
	private Boolean active;
}
