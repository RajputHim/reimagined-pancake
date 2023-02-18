package com.GorgeousGlam.controller;

import java.util.List;

import javax.security.auth.login.LoginException;
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
import com.GorgeousGlam.exception.CustomerException;
import com.GorgeousGlam.exception.SessionException;
import com.GorgeousGlam.model.Address;
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

	@PostMapping("/customers/addresses/{customerId}")
	public ResponseEntity<Customer> addCustomerAddressHandler(@Valid @RequestBody Address address,
			@PathVariable("customerId") Integer customerId, @RequestParam("sessionKey") String sessionKey) {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session == null) {
			throw new SessionException("Please login with the correct credentials");
		}

		Customer savedCustomer = null;

		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			savedCustomer = customerService.addAddress(address, customerId);

		}

		return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);

	}

	@GetMapping("/customers")
	public ResponseEntity<Customer> getCustomerByIdHandler(@RequestParam("customerId") Integer customerId,
			@RequestParam("sessionKey") String sessionKey) {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session == null) {
			throw new SessionException("Please login with the correct credentials");
		}

		Customer customer = null;

		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			customer = customerService.getCustomerDetailsById(customerId);

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
			@RequestParam("sessionKey") String sessionKey) throws CustomerException, LoginException {

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

	@DeleteMapping("/customers/addresses/{customerId}/{addressId}")
	public ResponseEntity<Customer> deleteAddressByIdHandler(@PathVariable("customerId") Integer customerId,
			@PathVariable("addressId") Integer addressId, @RequestParam("sessionKey") String sessionKey)
			throws CustomerException, LoginException {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session == null) {
			throw new SessionException("Please login with the correct credentials");
		}

		Customer updatedCustomer = null;

		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			updatedCustomer = customerService.deleteAddress(addressId, customerId);

		}

		return new ResponseEntity<>(updatedCustomer, HttpStatus.ACCEPTED);

	}

	@PatchMapping("/customers/{userId}/{addressId}")
	public ResponseEntity<Customer> updateCustomerDetailsHandler(@PathVariable("addressId") Integer addressId,
			@PathVariable("customerId") Integer customerId, @RequestParam("sessionKey") String sessionKey,
			@Valid @RequestBody CustomerDTO customer) {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session == null) {
			throw new SessionException("Please login with the correct credentials");
		}

		Customer updatedCustomer = null;

		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			updatedCustomer = customerService.updateCustomerDetails(customer, addressId, customerId);

		}

		return new ResponseEntity<>(updatedCustomer, HttpStatus.ACCEPTED);

	}

}
