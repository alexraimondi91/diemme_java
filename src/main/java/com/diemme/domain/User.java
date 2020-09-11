package com.diemme.domain;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "user")
@Data @NoArgsConstructor @AllArgsConstructor
public class User extends BaseModel{

	@Column(name = "name", nullable = false)
    @NotEmpty(message = "*Please provide your name")
	private String name;
	@Column(name = "user_name", nullable = false, unique = true)
	@Length(min = 5, message = "*Your user name must have at least 5 characters")
    @NotEmpty(message = "*Please provide a user name")
    private String userName;
	@Column(name = "surname", nullable = false)
	@NotEmpty
	private String surname;
	@Column(name = "email", nullable = false)
	@Email 
	@Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
	private String email;
	@Column(name = "password", nullable = false)
	@Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
	private String password;	

	@Column(name = "active", nullable = true)
	private Boolean active;
	
	@Column(name = "fiscal_code", nullable = true)
	private String fiscalCode;
	@Column(name = "address_shipment", nullable = true)
	private String addressShipment;
	
	
	@Column(name = "p_iva", nullable = true)
	private String pIva;
	
	@Column(name = "company_name", nullable = true)
	private String companyName;

	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Message> messages;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( 
        name = "user_role", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id", nullable = true), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id")) 
	private Set<Role> roles;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private List<ProductShowcase> productShowcases;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private List<TechnologyShowcase> technologyShowcases;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private List<QuotationShowcase> quotationShowcases;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private List<NewsShowcase> newsShowcases;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private List<ContactShowcase> contactShowcases;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( 
        name = "client_layout", 
        joinColumns = @JoinColumn(
          name = "client_id", referencedColumnName = "id", nullable = true), 
        inverseJoinColumns = @JoinColumn(
          name = "layout_id", referencedColumnName = "id")) 
	private Set<Layout> layout;
	
	
	
}
