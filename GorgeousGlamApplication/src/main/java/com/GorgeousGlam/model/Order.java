package com.GorgeousGlam.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingOrderId;

	private LocalDateTime orderDate;

	private String transactionMode;

	private Integer quantity;

	private double totalCost;

	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@OneToOne
//	@JsonIgnore
	private Cart cart;
}
