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
	private Integer bookingOrderId;
	
	@NotNull(message = "Transaction mode cannot be null")
	private String transactionMode;

	private Double totalCost;

	private Integer totalQuantity;
	
	private LocalDateTime orderDateTime;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@OneToOne
	@JsonIgnore
	private Cart cart;

}
