package com.GorgeousGlam.model;

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

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;

	@NotNull
	@NotBlank
	@NotEmpty
	private String name;
	
	@Email
	private String email;
	
	@Size(min = 8, max = 16, message = "Password length should be minimum 8 and maximum 16")
	private String password;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart = new Cart();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customer")
	private List<Orders> orders;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
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
