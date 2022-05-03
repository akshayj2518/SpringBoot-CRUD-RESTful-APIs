package com.homelane.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Home {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long houseId;
	
	private double price;
	private double bedrooms;
	private double bathrooms;
	private long sqft_living;
	private long sqft_lot;
	private double floors;
	private int waterfront;
	private int view;
	private int condition;
	private long sqft_above;
	private long sqft_basement;
	private int yr_built;
	private int yr_renovated;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressId")
	private Address address;

}
