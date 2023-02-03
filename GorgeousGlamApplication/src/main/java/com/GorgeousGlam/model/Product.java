package com.GorgeousGlam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

	@NotNull(message = "Product name cannot be null")
	private String product_name;

	@Min(value = 1)
	private Integer product_quantity;

	@Min(value = 1)
	private Double product_price;

	@NotNull(message = "Product brand name cannot be null")
	private String product_brand;

	private Double product_rating;

	private String product_type;

	private String category;

	private String weight;

}
