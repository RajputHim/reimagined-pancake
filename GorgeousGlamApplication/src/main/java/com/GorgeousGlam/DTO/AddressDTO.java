package com.GorgeousGlam.DTO;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
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
}
