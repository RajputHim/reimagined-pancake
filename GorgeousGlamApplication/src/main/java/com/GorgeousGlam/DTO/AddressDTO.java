package com.GorgeousGlam.DTO;

import javax.validation.constraints.NotNull;

import lombok.Data;

public class AddressDTO {
	@NotNull(message = "House number cannot be null")
	private String houseNo;

	private String colony;
	@NotNull(message = "City name cannot be null")

	private String city;
	@NotNull(message = "State name cannot be null")

	private String state;
	@NotNull(message = "Pincode cannot be null")
	private Integer pinCode;

	public AddressDTO() {
		// TODO Auto-generated constructor stub
	}

	public AddressDTO(@NotNull(message = "House number cannot be null") String houseNo, String colony,
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

}
