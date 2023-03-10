package com.GorgeousGlam.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
	public Cart addProductToCart(Integer productId, Integer customerId, Integer quantityOfProduct)
			throws ProductNotFoundException, CustomerException {

		/*
		 * This method will add the product into the cart by fetching product with id
		 * and return the updated cart
		 */
		Customer customer = customerService.getCustomerDetailsById(customerId);

		if (customer != null) {

			Optional<Product> productOpt = productRepo.findById(productId);

			if (productOpt.isPresent()) {

				Product existingProduct = productOpt.get();
				if (existingProduct.getProductQuantity() >= quantityOfProduct && quantityOfProduct != 0) {
					Cart pCart = customer.getCart();

					Map<Product, Integer> productList = pCart.getProducts();

					boolean isPresent = productList.containsKey(existingProduct);

					if (isPresent) {
						Integer qty = productList.get(existingProduct);

						Integer stock = existingProduct.getProductQuantity();

						if (qty + quantityOfProduct > stock || quantityOfProduct == 0) {
							throw new ProductNotFoundException(
									"Minimum quantity should be 1 and maximum quantity should be " + stock);
						}
						productList.put(existingProduct, qty + quantityOfProduct);

					} else {
						productList.put(existingProduct, quantityOfProduct);
					}

					cartRepo.save(pCart);
					return customer.getCart();

				} else {
					throw new ProductNotFoundException("Minimum quantity should be 1 and maximum quantity should be "
							+ existingProduct.getProductQuantity());
				}

			} else {
				throw new ProductNotFoundException("No product found by id: " + productId);
			}

		} else {
			throw new CustomerException("No customer found by id:" + customerId);
		}

	}

	@Override
	public Cart viewCartbyId(Integer customerId) throws CustomerException, CartException {

		/*
		 * This method will take customer id as input and then fetch customer details
		 * and return the cart details from the customer details.
		 */

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

		/*
		 * This method will fetch customer and product details through respective id's.
		 * Then it will add product into the cart which will be fetched from customer
		 * details.
		 */
		Customer customer = customerService.getCustomerDetailsById(customerId);

		if (customer != null) {
			Cart pCart = customer.getCart();

			Map<Product, Integer> products = pCart.getProducts();

			Set<Product> productSet = products.keySet();

			for (Product product : productSet) {
				if (product.getProductId() == productId) {
					products.remove(product);
					return cartRepo.save(pCart);

				}
			}

			throw new ProductNotFoundException("No product found by id: " + productId);

		} else {
			throw new CustomerException("No customer found by id:" + customerId);
		}

	}

	@Override
	public Cart changeProductQuantity(Integer productId, Integer customerId, Integer newQuantity)
			throws ProductNotFoundException, CustomerException {

		/*
		 * This method will fetch the customer details through id. Then find the product
		 * from the cart which will be fetched from customer details. Then update the
		 * old quantity with new quantity.
		 */

		Customer customer = customerService.getCustomerDetailsById(customerId);

		if (customer != null) {
			Cart pCart = customer.getCart();

			Map<Product, Integer> products = pCart.getProducts();

			Product productInStore = productRepo.findById(productId)
					.orElseThrow(() -> new ProductNotFoundException("Product not found in store by id: " + productId));

			if (newQuantity <= productInStore.getProductQuantity() && newQuantity > 0) {

				products.put(productInStore, newQuantity);

				return cartRepo.save(pCart);

			} else {
				throw new ProductNotFoundException("Minimum quantity should be 1 and maximum quantity should be "
						+ productInStore.getProductQuantity());
			}

		} else {
			throw new CustomerException("No customer found by id:" + customerId);
		}

	}

	@Override
	public Map<Product, Integer> emptyCart(Integer cartId) throws CartException {
		
		/*
		 * This method will empty the cart by fetching cart details through the cart id.
		 */		
		
		Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new CartException("No cart found by id: " + cartId));

		Map<Product, Integer> products = cart.getProducts();
		cart.setProducts(new HashMap<Product, Integer>());

		cartRepo.save(cart);
		return products;
	}

}
