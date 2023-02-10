package com.GorgeousGlam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Size;

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

	@Size(min = 1, message = "Minimum quantity should not be less than 1")
	Integer quantity;

	@Override
	public Cart addProductToCart(Integer productId, Integer customerId, Integer quantityOfProduct)
			throws ProductNotFoundException, CustomerException {

		quantity = quantityOfProduct;
		Customer customer = customerService.getCustomerDetailsById(customerId);

		if (customer != null) {

			Optional<Product> productOpt = productRepo.findById(productId);

			if (productOpt.isPresent()) {
				Cart pCart = customer.getCart();

				Product existingProduct = productOpt.get();

				Product product = new Product(productId, existingProduct.getProductName(), quantity,
						existingProduct.getProductPrice(), existingProduct.getProductBrand(),
						existingProduct.getProductRating(), existingProduct.getProductType(),
						existingProduct.getCategory(), existingProduct.getWeight());

				pCart.getProducts().add(product);

				return cartRepo.save(pCart);

			} else {
				throw new ProductNotFoundException("No product found by id: " + productId);
			}

		} else {
			throw new CustomerException("No customer found by id:" + customerId);
		}

	}

	@Override
	public Cart viewCartbyId(Integer customerId) throws CustomerException, CartException {
		Customer customer = customerService.getCustomerDetailsById(customerId);

		if (customer != null) {
			int cartId = customerService.getCustomerDetailsById(customerId).getCart().getCartId();
			return cartRepo.findById(cartId).orElseThrow(() -> new CartException("No cart found by id: " + cartId));

		} else {
			throw new CustomerException("No customer found by id:" + customerId);
		}
	}

	@Override
	public Cart deleteProductFromCart(Integer productId, Integer customerId)
			throws ProductNotFoundException, CustomerException {
		Customer customer = customerService.getCustomerDetailsById(customerId);

		if (customer != null) {
			Cart pCart = customer.getCart();

			List<Product> products = pCart.getProducts();
			boolean removed = false;
			for (Product product : products) {
				if (product.getProductId() == productId) {
					products.remove(products.indexOf(product));
					removed = true;
				}
			}

			if (removed) {

				return cartRepo.save(pCart);

			} else {
				throw new ProductNotFoundException("No product found by id: " + productId);
			}

		} else {
			throw new CustomerException("No customer found by id:" + customerId);
		}

	}

	@Override
	public Cart changeProductQuantity(Integer productId, Integer customerId, Integer newQuantity)
			throws ProductNotFoundException, CustomerException {

		Customer customer = customerService.getCustomerDetailsById(customerId);

		if (customer != null) {
			Cart pCart = customer.getCart();

			List<Product> products = pCart.getProducts();
			boolean updated = false;
			for (Product product : products) {
				if (product.getProductId() == productId) {

					Product productInStore = productRepo.findById(productId).orElseThrow(
							() -> new ProductNotFoundException("Product not found in store by id: " + productId));

					if (productInStore.getProductQuantity() >= newQuantity) {
						product.setProductQuantity(newQuantity);
						updated = true;

					} else {
						throw new ProductNotFoundException(
								"Please enter quantity greater than or equal to 1 or less than or equal to "
										+ productInStore.getProductQuantity());
					}

				}
			}

			if (updated) {

				return cartRepo.save(pCart);

			} else {
				throw new ProductNotFoundException("No product found by id: " + productId);
			}

		} else {
			throw new CustomerException("No customer found by id:" + customerId);
		}

	}

	@Override
	public List<Product> emptyCart(Integer cartId) throws CartException {

		Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new CartException("No cart found by id: " + cartId));

		List<Product> products = cart.getProducts();
		cart.setProducts(new ArrayList<>());

		cartRepo.save(cart);
		return products;
	}

}
