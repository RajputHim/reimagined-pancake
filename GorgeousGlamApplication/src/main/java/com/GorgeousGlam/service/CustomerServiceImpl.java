package com.GorgeousGlam.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GorgeousGlam.DTO.AddressDTO;
import com.GorgeousGlam.DTO.CustomerDTO;
import com.GorgeousGlam.exception.AddressException;
import com.GorgeousGlam.exception.CustomerException;
import com.GorgeousGlam.model.Address;
import com.GorgeousGlam.model.Customer;
import com.GorgeousGlam.repository.AddressRepo;
import com.GorgeousGlam.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private ILoginService loginService;

	@Override
	public Customer addCustomer(CustomerDTO customer) throws CustomerException {

		/*
		 * This method will check if the customer details is present into the database
		 * or not, then it will save data or throw exception.
		 */

		if (customerRepo.findByEmail(customer.getEmail()) != null) {
			throw new CustomerException("Customer already exists with email: " + customer.getEmail());
		}

		Customer newCustomer = new Customer(customer.getName(), customer.getEmail(), customer.getPassword());

		Address customerAddress = new Address(customer.getAddress().getHouseNo(), customer.getAddress().getColony(),
				customer.getAddress().getCity(), customer.getAddress().getState(), customer.getAddress().getPinCode());

		customerAddress.setCustomer(newCustomer);

		newCustomer.getAddress().add(customerAddress);

		Customer savedCustomer = customerRepo.save(newCustomer);

		return savedCustomer;
	}

	@Override
	public Customer getCustomerDetailsById(Integer customerId) throws CustomerException {

		/*
		 * This method will return the customer details by fetching the data from
		 * database through id.
		 */

		return customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException("No customer found by id: " + customerId));
	}

	@Override
	public Customer deleteCustomerById(Integer customerId) throws CustomerException, LoginException {

		/*
		 * This method will delete the customer data through customer id. And also
		 * delete the login session.
		 */

		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException("No customer found by id: " + customerId));

		loginService.logout(customerId);

		customerRepo.delete(customer);
		return customer;
	}

	@Override
	public Customer updateCustomerDetails(CustomerDTO customer, Integer addressId, Integer customerId)
			throws CustomerException {

		/*
		 * This method will replace the old customer data with the new data and the
		 * return the updated customer details.
		 */

		Customer existingCustomer = customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException("No customer found by id: " + customerId));

		existingCustomer.setEmail(customer.getEmail());

		existingCustomer.setName(customer.getName());

		existingCustomer.setPassword(customer.getPassword());

		AddressDTO newAddress = customer.getAddress();

		List<Address> allAddresses = existingCustomer.getAddress();

		for (Address address : allAddresses) {
			if (address.getAddressId() == addressId) {
				address.setCity(newAddress.getCity());
				address.setColony(newAddress.getColony());
				address.setHouseNo(newAddress.getHouseNo());
				address.setPinCode(newAddress.getPinCode());
				address.setState(newAddress.getState());
				return customerRepo.save(existingCustomer);
			}

		}

		throw new AddressException("Address id is wrong..");

	}

	@Override
	public List<Customer> getAllCustomers() throws CustomerException {

		/*
		 * This method will return all the registered customers from the database.
		 */
		List<Customer> allCustomers = customerRepo.findAll();

		if (allCustomers.isEmpty()) {
			throw new CustomerException("No customers found..");
		}

		return allCustomers;
	}

	@Override
	public Customer addAddress(Address address, Integer customerId) throws AddressException {

		/*
		 * This method will fetch customer data through id and then it will add new
		 * address into the address list of customer.
		 */

		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException("No customer found by id: " + customerId));

		address.setCustomer(customer);
		customer.getAddress().add(address);

		customerRepo.save(customer);

		return customer;
	}

	@Override
	public Customer deleteAddress(Integer addressId, Integer customerId) throws AddressException {

		/*
		 * This method will delete the customer address by fetching the customer details
		 * through id, and return the updated customer data.
		 */

		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException("No customer found by id: " + customerId));

		List<Address> allAddresses = customer.getAddress();

		for (Address address : allAddresses) {
			if (address.getAddressId() == addressId) {
				allAddresses.remove(address);
				customer.setAddress(allAddresses);
				addressRepo.deleteById(addressId);
				return customer;
			}
		}

		throw new AddressException("No address found by id: " + addressId);

	}

}
