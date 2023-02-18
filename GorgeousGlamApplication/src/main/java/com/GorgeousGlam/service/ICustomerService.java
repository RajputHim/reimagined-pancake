package com.GorgeousGlam.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.GorgeousGlam.DTO.CustomerDTO;
import com.GorgeousGlam.exception.AddressException;
import com.GorgeousGlam.exception.CustomerException;
import com.GorgeousGlam.model.Address;
import com.GorgeousGlam.model.Customer;

public interface ICustomerService {
	public Customer addCustomer(CustomerDTO customer) throws CustomerException;

	public Customer getCustomerDetailsById(Integer customerId) throws CustomerException;

	public Customer deleteCustomerById(Integer customerId) throws CustomerException, LoginException;

	public Customer updateCustomerDetails(CustomerDTO customer, Integer addressId, Integer customerId)
			throws CustomerException;

	public List<Customer> getAllCustomers() throws CustomerException;

	public Customer addAddress(Address address, Integer customerId) throws AddressException;

	public Customer deleteAddress(Integer addressId, Integer customerId) throws AddressException;

}
