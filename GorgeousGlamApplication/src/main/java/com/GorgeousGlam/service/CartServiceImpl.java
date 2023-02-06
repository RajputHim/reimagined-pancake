package com.GorgeousGlam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GorgeousGlam.exception.CartException;
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
	public Cart viewCartbyId(Integer cartId, Integer customerId) throws CustomerException, CartException {
		Customer customer = customerService.getCustomerDetailsById(customerId);

		if (customer != null) {

			return cartRepo.findById(cartId).orElseThrow(() -> new CartException("No cart found by id: " + cartId));

		} else {
			throw new CustomerException("No customer found by id:" + customerId);
		}
	}

	@Override
	public Cart deleteProductFromCart(Integer product_Id, Integer customerId)
			throws ProductNotFoundException, CustomerException {
		Customer customer = customerService.getCustomerDetailsById(customerId);

		if (customer != null) {
			Cart pCart = customer.getCart();

			List<Product> products = pCart.getProducts();
			boolean removed = false;
			for (Product product : products) {
				if (product.getProduct_id() == product_Id) {
					products.remove(products.indexOf(product));
					removed = true;
				}
			}

			if (removed) {

				return cartRepo.save(pCart);

			} else {
				throw new ProductNotFoundException("No product found by id: " + product_Id);
			}

		} else {
			throw new CustomerException("No customer found by id:" + customerId);
		}

	}

	@Override
	public Cart changeProductQuantity(Integer product_Id, Integer customerId, Integer newQuantity)
			throws ProductNotFoundException, CustomerException {

		Customer customer = customerService.getCustomerDetailsById(customerId);

		if (customer != null) {
			Cart pCart = customer.getCart();

			List<Product> products = pCart.getProducts();
			boolean updated = false;
			for (Product product : products) {
				if (product.getProduct_id() == product_Id) {

					Product productInStore = productRepo.findById(product_Id).orElseThrow(
							() -> new ProductNotFoundException("Product not found in store by id: " + product_Id));

					if (productInStore.getProduct_quantity() >= newQuantity) {
						product.setProduct_quantity(newQuantity);
						updated = true;

					} else {
						throw new ProductNotFoundException(
								"Please enter quantity lesser or equal to " + productInStore.getProduct_quantity());
					}

				}
			}

			if (updated) {

				return cartRepo.save(pCart);

			} else {
				throw new ProductNotFoundException("No product found by id: " + product_Id);
			}

		} else {
			throw new CustomerException("No customer found by id:" + customerId);
		}

	}

}
