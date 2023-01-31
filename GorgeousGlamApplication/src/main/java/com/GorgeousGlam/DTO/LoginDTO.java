package com.GorgeousGlam.DTO;

import com.GorgeousGlam.model.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

	private String email;
	private String password;
	private UserType userType;

}
