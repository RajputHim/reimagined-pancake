package com.GorgeousGlam.model;

import java.util.Objects;

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

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer addressId;

	@NotNull(message = "House number cannot be null")
	private String houseNo;

	private String colony;

	@NotNull(message = "City name cannot be null")
	private String city;

	@NotNull(message = "State name cannot be null")
	private String state;

	@NotNull(message = "Pincode cannot be null")
	private Integer pinCode;

//	@JsonIgnore
//	private boolean primary;

	@ManyToOne
	@JsonIgnore
	private Customer customer;

//	@JsonIgnore
//	@OneToOne
//	private Orders order;

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(@NotNull(message = "House number cannot be null") String houseNo, String colony,
			@NotNull(message = "City name cannot be null") String city,
			@NotNull(message = "State name cannot be null") String state,
			@NotNull(message = "Pincode cannot be null") Integer pinCode) {
		super();
		this.houseNo = houseNo;
		this.colony = colony;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getColony() {
		return colony;
	}

	public void setColony(String colony) {
		this.colony = colony;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, colony, houseNo, pinCode, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(colony, other.colony)
				&& Objects.equals(houseNo, other.houseNo) && Objects.equals(pinCode, other.pinCode)
				&& Objects.equals(state, other.state);
	}

}
