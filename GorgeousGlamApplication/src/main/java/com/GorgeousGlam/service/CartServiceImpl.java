package com.GorgeousGlam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GorgeousGlam.exception.CustomerException;
import com.GorgeousGlam.exception.ProductNotFoundException;
import com.GorgeousGlam.model.Cart;
import com.GorgeousGlam.model.Customer;
import com.GorgeousGlam.model.Product;
import com.GorgeousGlam.repository.CartRepo;
import com.GorgeousGlam.repository.ProductRepo;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private ICustomerService customerService;

	@Override
	public Cart addProductToCart(Integer product_Id, Integer customerId)
			throws ProductNotFoundException, CustomerException {

		Customer customer = customerService.getCustomerDetailsById(customerId);

		if (customer != null) {

			Optional<Product> productOpt = productRepo.findById(product_Id);

			if (productOpt.isPresent()) {
				Cart pCart = customer.getCart();

				pCart.getProducts().add(productOpt.get());

				return cartRepo.save(pCart);

			} else {
				throw new ProductNotFoundException("No product found by id: " + product_Id);
			}

		} else {
			throw new CustomerException("No customer found by id:" + customerId);
		}

	}

	@Override
	public Cart viewCartbyId(Integer cartId, Integer customerId) throws CustomerException {
		Customer customer = customerService.getCustomerDetailsById(customerId);

		if (customer != null) {

			return cartRepo.findById(cartId).orElseThrow(() -> new CustomerException());

		} else {
			throw new CustomerException("No customer found by id:" + customerId);
		}
	}

}
