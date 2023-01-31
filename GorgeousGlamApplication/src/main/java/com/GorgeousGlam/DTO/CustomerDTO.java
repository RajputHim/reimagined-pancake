package com.GorgeousGlam.DTO;

import lombok.Data;

@Data
public class CustomerDTO {

	private String email;

	private String password;

	private String name;

	private AddressDTO address;
}
