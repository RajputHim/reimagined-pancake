package com.GorgeousGlam.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	@JsonProperty(access = Access.READ_ONLY)
	private Integer productId;

	@NotNull(message = "Product name cannot be null")
	private String productName;

	@Min(value = 1)
	private Integer productQuantity;

	@Min(value = 1)
	private Double productPrice;

	@NotNull(message = "Product brand name cannot be null")
	private String productBrand;

	private Double productRating;

	private String productType;

	@NotNull(message = "Category cannot be null")
	@NotBlank(message = "Category cannot be blank")
	@NotEmpty(message = "Category cannot be empty")
	private String category;

	private String weight;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category) && Objects.equals(productBrand, other.productBrand)
				&& Objects.equals(productName, other.productName) && Objects.equals(productPrice, other.productPrice)
				&& Objects.equals(productType, other.productType) && Objects.equals(weight, other.weight);
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, productBrand, productName, productPrice, productType, weight);
	}

}
