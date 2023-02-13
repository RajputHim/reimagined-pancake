package com.GorgeousGlam.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GorgeousGlam.exception.OrderException;
import com.GorgeousGlam.model.Orders;
import com.GorgeousGlam.model.Shipment;
import com.GorgeousGlam.repository.OrderRepo;
import com.GorgeousGlam.repository.ShipmentRepo;

@Service
public class ShipmentServiceImpl implements IShipmentService {

	@Autowired
	private ShipmentRepo shipmentRepo;

	@Autowired
	private OrderRepo orderRepo;

	@Override
	public Shipment addShipment(Integer orderId) {

		Orders order = orderRepo.findById(orderId)
				.orElseThrow(() -> new OrderException("No order found by id: " + orderId));

		Shipment shipment = new Shipment();

		shipment.setDeliveryFrom("Super novelty");
		LocalDate deliveryDate = LocalDate.now().plusDays(5);

		shipment.setExpectedDelivery(deliveryDate);

		shipment.setOrder(order);

		return shipmentRepo.save(shipment);
	}

}
