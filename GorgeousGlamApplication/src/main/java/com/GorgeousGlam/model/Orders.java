package com.GorgeousGlam.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer bookingOrderId;

	@NotNull(message = "Transaction mode cannot be null")
	private String transactionMode;

	@JsonProperty(access = Access.READ_ONLY)
	private Double totalCost;

	@JsonProperty(access = Access.READ_ONLY)
	private Integer totalQuantity;

	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime orderDateTime;

	@JsonProperty(access = Access.READ_ONLY)
	private String orderStatus;

	@JsonProperty(access = Access.READ_ONLY)
	@ManyToOne
	private Customer customer;

	@OneToOne
//	@JsonIgnore
//	@JsonProperty(access = Access.READ_ONLY)
	@JsonIgnore
	private Cart cart;

}
