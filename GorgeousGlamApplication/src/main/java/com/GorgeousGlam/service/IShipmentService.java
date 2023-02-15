package com.GorgeousGlam.service;

import com.GorgeousGlam.model.Shipment;

public interface IShipmentService {

	public Shipment addShipment(Integer orderId);

	public Shipment viewShipment(Integer shipmentId);

	public Shipment deleteShipment(Integer shipmentId);

}
