package com.diemme.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "Client")
public class Client extends User{
	
	@Column(name = "fiscal_code", nullable = false)
	@NotBlank
	private String fiscalCode;
	@Column(name = "address_shipment", nullable = false)
	@NotBlank
	private String addressShipment;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( 
        name = "client_layout", 
        joinColumns = @JoinColumn(
          name = "client_id", referencedColumnName = "id", nullable = true), 
        inverseJoinColumns = @JoinColumn(
          name = "layout_id", referencedColumnName = "id")) 
	private Set<Layout> layout;

}
