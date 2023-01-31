package com.GorgeousGlam.service;

import com.GorgeousGlam.DTO.CustomerDTO;
import com.GorgeousGlam.exception.CustomerException;
import com.GorgeousGlam.model.Customer;

public interface ICustomerService {
	public Customer addCustomer(CustomerDTO customer) throws CustomerException;
}
