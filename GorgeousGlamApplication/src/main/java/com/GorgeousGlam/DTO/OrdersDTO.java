package com.GorgeousGlam.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.GorgeousGlam.model.Product;

public class OrdersDTO {

	private Integer bookingOrderId;

	private String transactionMode;

	private Double totalCost;

	private Integer totalQuantity;

	private LocalDateTime orderDateTime;

	private String name;

	private String email;

	private AddressDTO address;

	private Map<Product, Integer> products;

	public OrdersDTO() {
		// TODO Auto-generated constructor stub
	}

	public OrdersDTO(Integer bookingOrderId, String transactionMode, Double totalCost, Integer totalQuantity,
			LocalDateTime orderDateTime, String name, String email) {
		super();
		this.bookingOrderId = bookingOrderId;
		this.transactionMode = transactionMode;
		this.totalCost = totalCost;
		this.totalQuantity = totalQuantity;
		this.orderDateTime = orderDateTime;
		this.name = name;
		this.email = email;
	}

	public Integer getBookingOrderId() {
		return bookingOrderId;
	}

	public void setBookingOrderId(Integer bookingOrderId) {
		this.bookingOrderId = bookingOrderId;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
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

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}

}
