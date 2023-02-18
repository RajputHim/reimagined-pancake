package com.GorgeousGlam.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer customerId;

	@NotNull(message = "Customer name cannot be null")
	@NotBlank(message = "Customer name cannot be blank")
	@NotEmpty(message = "Customer name cannot be empty")
	private String name;

	@Email
	private String email;

	@Size(min = 8, max = 16, message = "Password length should be minimum 8 and maximum 16")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customer")
	@JsonProperty(access = Access.READ_ONLY)
	private List<Address> address = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JsonProperty(access = Access.READ_ONLY)
	private Cart cart = new Cart();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	@JsonProperty(access = Access.READ_ONLY)
//	@JsonIgnore
	private List<Orders> orders = new ArrayList<>();

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

//	public List<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}

}
