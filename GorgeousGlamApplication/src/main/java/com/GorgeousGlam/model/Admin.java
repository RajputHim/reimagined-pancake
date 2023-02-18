package com.GorgeousGlam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@JsonProperty(access = Access.READ_ONLY)
	private Integer adminId;

	@NotNull(message = "Admin name cannot be null")
	@NotBlank(message = "Admin name cannot be blank")
	@NotEmpty(message = "Admin name cannot be empty")
	private String name;

	@Email
	private String email;

	@Size(min = 8, max = 16, message = "Password length should be minimum 8 and maximum 16")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

}
