package com.GorgeousGlam.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Shipment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int shipmentId;

	@NotNull(message = "DeliveryFrom cannot be null")
	private String deliveryFrom;

	@NotNull
	private LocalDate expectedDelivery;

	@OneToOne
	private Orders order;

	public Shipment() {
		// TODO Auto-generated constructor stub
	}

	public Shipment(int shipmentId, String deliveryFrom, LocalDate expectedDelivery, Orders order) {
		super();
		this.shipmentId = shipmentId;
		this.deliveryFrom = deliveryFrom;
		this.expectedDelivery = expectedDelivery;
		this.order = order;
	}

	public int getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}

	public LocalDate getExpectedDelivery() {
		return expectedDelivery;
	}

	public void setExpectedDelivery(LocalDate expectedDelivery) {
		this.expectedDelivery = expectedDelivery;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public String getDeliveryFrom() {
		return deliveryFrom;
	}

	public void setDeliveryFrom(String deliveryFrom) {
		this.deliveryFrom = deliveryFrom;
	}

	@Override
	public String toString() {
		return "Shipment [shipmentId=" + shipmentId + ", expectedDelivery=" + expectedDelivery + ", order=" + order
				+ "]";
	}

}
