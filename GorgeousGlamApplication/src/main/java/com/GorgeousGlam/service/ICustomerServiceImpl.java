package com.GorgeousGlam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GorgeousGlam.DTO.CustomerDTO;
import com.GorgeousGlam.exception.CustomerException;
import com.GorgeousGlam.model.Address;
import com.GorgeousGlam.model.Customer;
import com.GorgeousGlam.repository.CustomerRepo;

@Service
public class ICustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public Customer addCustomer(CustomerDTO customer) throws CustomerException {

		if (customerRepo.findByEmail(customer.getEmail()) != null) {
			throw new CustomerException("Customer already exists with email: " + customer.getEmail());
		}

		Customer newCustomer = new Customer(customer.getName(), customer.getEmail(), customer.getPassword());

		Address customerAddress = new Address(customer.getAddress().getHouseNo(), customer.getAddress().getColony(),
				customer.getAddress().getCity(), customer.getAddress().getState(), customer.getAddress().getPinCode());

		newCustomer.setAddress(customerAddress);

		Customer savedCustomer = customerRepo.save(newCustomer);

		return savedCustomer;
	}

	@Override
	public Customer getCustomerDetailsById(Integer customerId) throws CustomerException {

		return customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException("No customer found by id: " + customerId));
	}

	@Override
	public Customer deleteCustomerById(Integer customerId) throws CustomerException {
		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException("No customer found by id: " + customerId));

		customerRepo.delete(customer);
		return customer;
	}

	@Override
	public Customer updateCustomerDetails(Customer customer) throws CustomerException {
		Customer existingCustomer = customerRepo.findById(customer.getCustomerId())
				.orElseThrow(() -> new CustomerException("No customer found by id: " + customer.getCustomerId()));

		if (customer.getEmail() != null) {
			existingCustomer.setEmail(customer.getEmail());
		}

		if (customer.getName() != null) {
			existingCustomer.setName(customer.getName());
		}

		if (customer.getPassword() != null) {
			existingCustomer.setPassword(customer.getPassword());
		}

		if (customer.getAddress() != null) {
			Address newAddress = customer.getAddress();
			Address oldAddress = existingCustomer.getAddress();

			if (newAddress.getCity() != null) {
				oldAddress.setCity(newAddress.getCity());
			}

			if (newAddress.getColony() != null) {
				oldAddress.setColony(newAddress.getColony());
			}

			if (newAddress.getHouseNo() != null) {
				oldAddress.setHouseNo(newAddress.getHouseNo());
			}

			if (newAddress.getState() != null) {
				oldAddress.setState(newAddress.getState());
			}

			if (newAddress.getPinCode() != null) {
				oldAddress.setPinCode(newAddress.getPinCode());
			}

		}

		return customerRepo.save(existingCustomer);
	}

	@Override
	public List<Customer> getAllCustomers() throws CustomerException {
		List<Customer> allCustomers = customerRepo.findAll();

		if (allCustomers.isEmpty()) {
			throw new CustomerException("No customers found..");
		}

		return allCustomers;
	}

}
