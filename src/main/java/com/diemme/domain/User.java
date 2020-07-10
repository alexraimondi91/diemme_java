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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "user")
@JsonIgnoreProperties(  {"handler","hibernateLazyInitializer"} )
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "user_name")
    private String userName;
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
	
	@Column(name = "country", nullable = false)
	@NotBlank
	private String country;
	@CreationTimestamp
	@Column(name = "insertDate")
	private ZonedDateTime insertDate;
	@Column(name = "active", nullable = false)
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
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( 
        name = "user_role", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id", nullable = true), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id")) 
	private Set<Role> roles;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( 
        name = "user_product_showcase", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id", nullable = true), 
        inverseJoinColumns = @JoinColumn(
          name = "product_showcase_id", referencedColumnName = "id")) 
	private Set<ProductShowcase> productShowcases;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( 
        name = "user_tecnology_showcase", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id", nullable = true), 
        inverseJoinColumns = @JoinColumn(
          name = "tecnology_showcase_id", referencedColumnName = "id")) 
	private Set<TechnologyShowcase> tecnologyShowcases;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( 
        name = "user_quotation_showcase", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id", nullable = true), 
        inverseJoinColumns = @JoinColumn(
          name = "quotation_showcase_id", referencedColumnName = "id")) 
	private Set<QuotationShowcase> quotationShowcases;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( 
        name = "user_news_showcase", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id", nullable = true), 
        inverseJoinColumns = @JoinColumn(
          name = "news_showcase_id", referencedColumnName = "id")) 
	private Set<NewsShowcase> newsShowcases;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( 
        name = "user_contact_showcase", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id", nullable = true), 
        inverseJoinColumns = @JoinColumn(
          name = "contact_showcase_id", referencedColumnName = "id")) 
	private Set<ContactShowcase> contactShowcases;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( 
        name = "client_layout", 
        joinColumns = @JoinColumn(
          name = "client_id", referencedColumnName = "id", nullable = true), 
        inverseJoinColumns = @JoinColumn(
          name = "layout_id", referencedColumnName = "id")) 
	private Set<Layout> layout;
	
	
	
}
