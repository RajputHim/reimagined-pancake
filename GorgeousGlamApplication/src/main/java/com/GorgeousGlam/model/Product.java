package com.GorgeousGlam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer product_id;

	private String product_name;

	private Integer product_quantity;

	private Double product_price;
	
	private String product_brand;
	
	private Double product_rating;
	
	
	
	

}
