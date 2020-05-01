package com.diemme.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "Designer")
public class Designer extends User {
	
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
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "layout_id", nullable = true)
	private Layout layout;
	
	

}
