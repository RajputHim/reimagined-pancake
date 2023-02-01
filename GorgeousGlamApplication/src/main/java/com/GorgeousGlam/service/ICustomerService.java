package com.GorgeousGlam.service;

import java.util.List;

import com.GorgeousGlam.DTO.CustomerDTO;
import com.GorgeousGlam.exception.CustomerException;
import com.GorgeousGlam.model.Customer;

public interface ICustomerService {
	public Customer addCustomer(CustomerDTO customer) throws CustomerException;

	public Customer getCustomerDetailsById(Integer customerId) throws CustomerException;

	public Customer deleteCustomerById(Integer customerId) throws CustomerException;

	public Customer updateCustomerDetails(Customer customer) throws CustomerException;

	public List<Customer> getAllCustomers() throws CustomerException;
}
