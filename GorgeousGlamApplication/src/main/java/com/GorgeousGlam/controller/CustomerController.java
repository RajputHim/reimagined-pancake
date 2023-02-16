package com.GorgeousGlam.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.GorgeousGlam.DTO.CustomerDTO;
import com.GorgeousGlam.exception.SessionException;
import com.GorgeousGlam.model.Customer;
import com.GorgeousGlam.model.Session;
import com.GorgeousGlam.model.UserType;
import com.GorgeousGlam.service.ICustomerService;
import com.GorgeousGlam.service.ISessionService;

@RestController
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private ISessionService sessionService;

	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody CustomerDTO customer) {

		Customer savedCustomer = customerService.addCustomer(customer);

		return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);

	}

	@GetMapping("/customers/{userId}")
	public ResponseEntity<Customer> getCustomerByIdHandler(@PathVariable("userId") Integer userId,
			@RequestParam("sessionKey") String sessionKey) {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session == null) {
			throw new SessionException("Please login with the correct credentials");
		}

		Customer customer = null;

		if (session.getUserId() == userId
				&& (session.getUserType() == UserType.ADMIN || session.getUserType() == UserType.CUSTOMER)) {

			customer = customerService.getCustomerDetailsById(userId);

		}

		return new ResponseEntity<>(customer, HttpStatus.OK);

	}

	@GetMapping("/customers/{adminId}")
	public ResponseEntity<List<Customer>> getAllCustomersHandler(@PathVariable("adminId") Integer userId,
			@RequestParam("sessionKey") String sessionKey) {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session == null) {
			throw new SessionException("Please login with the correct credentials");
		}

		List<Customer> allCustomers = null;

		if (session.getUserId() == userId && session.getUserType() == UserType.ADMIN) {

			allCustomers = customerService.getAllCustomers();
		} else {
			throw new SessionException("You are not authorized..");
		}

		return new ResponseEntity<>(allCustomers, HttpStatus.OK);

	}

	@DeleteMapping("/customers/{userId}")
	public ResponseEntity<Customer> deleteCustomerByIdHandler(@PathVariable("userId") Integer userId,
			@RequestParam("sessionKey") String sessionKey) {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session == null) {
			throw new SessionException("Please login with the correct credentials");
		}

		Customer deletedCustomer = null;

		if (session.getUserId() == userId
				&& (session.getUserType() == UserType.ADMIN || session.getUserType() == UserType.CUSTOMER)) {

			deletedCustomer = customerService.deleteCustomerById(userId);

		}

		return new ResponseEntity<>(deletedCustomer, HttpStatus.ACCEPTED);

	}

	@PatchMapping("/customers/{userId}")
	public ResponseEntity<Customer> updateCustomerDetailsHandler(@PathVariable("userId") Integer userId,
			@RequestParam("sessionKey") String sessionKey, @RequestBody Customer customer) {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session == null) {
			throw new SessionException("Please login with the correct credentials");
		}

		Customer updatedCustomer = null;

		if (session.getUserId() == userId && session.getUserType() == UserType.CUSTOMER) {

//			updatedCustomer = customerService.updateCustomerDetails(customer);

		}

		return new ResponseEntity<>(updatedCustomer, HttpStatus.ACCEPTED);

	}

}
