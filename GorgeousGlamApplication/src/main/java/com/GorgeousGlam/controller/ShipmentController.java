package com.GorgeousGlam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.GorgeousGlam.exception.CustomerException;
import com.GorgeousGlam.exception.SessionException;
import com.GorgeousGlam.model.Admin;
import com.GorgeousGlam.model.Session;
import com.GorgeousGlam.model.Shipment;
import com.GorgeousGlam.model.UserType;
import com.GorgeousGlam.service.IAdminService;
import com.GorgeousGlam.service.ISessionService;
import com.GorgeousGlam.service.IShipmentService;

@RestController
public class ShipmentController {

	@Autowired
	private IShipmentService shipmentService;

	@Autowired
	private IAdminService adminService;

	@Autowired
	private ISessionService sessionService;

	@PostMapping(value = "/shipments/{adminId}/{orderId}")
	public ResponseEntity<Shipment> addShipmentHandler(@PathVariable("orderId") Integer orderId,
			@PathVariable("adminId") Integer adminId, @RequestParam("sessionKey") String sessionKey)
			throws SessionException, CustomerException {

		Admin admin = adminService.getAdminDetailsById(adminId);

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session.getUserId() == adminId && session.getUserType() == UserType.ADMIN) {

			Shipment shipment = shipmentService.addShipment(orderId);

			return new ResponseEntity<>(shipment, HttpStatus.CREATED);

		} else {
			throw new SessionException("Please login with correct credentials");
		}
	}

	@GetMapping(value = "/shipments/{adminId}/{shipmentId}")
	public ResponseEntity<Shipment> viewShipmentByIdHandler(@PathVariable("adminId") Integer adminId,
			@PathVariable("shipmentId") Integer shipmentId, @RequestParam("sessionKey") String sessionKey)
			throws SessionException, CustomerException {

		Admin admin = adminService.getAdminDetailsById(adminId);

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session.getUserId() == adminId && session.getUserType() == UserType.ADMIN) {

			Shipment shipment = shipmentService.viewShipment(shipmentId);

			return new ResponseEntity<>(shipment, HttpStatus.CREATED);

		} else {
			throw new SessionException("Please login with correct credentials");
		}
	}

}
