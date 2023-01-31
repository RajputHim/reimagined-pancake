package com.GorgeousGlam.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;

	private String houseNo;

	private String colony;

	private String city;

	private String state;

	private Integer pinCode;

	@OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
	private Customer customer;
}
