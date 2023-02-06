package com.GorgeousGlam.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CustomerDTO {

	@Email
	private String email;

	private String password;

	@NotNull(message = "Customer name cannot be null")
	@NotBlank(message = "Customer name cannot be blank")
	@NotEmpty(message = "Customer name cannot be empty")
	private String name;

	@NotNull
	private AddressDTO address;
}
