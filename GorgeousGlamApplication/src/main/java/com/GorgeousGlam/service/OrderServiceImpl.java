package com.GorgeousGlam.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GorgeousGlam.DTO.AddressDTO;
import com.GorgeousGlam.DTO.OrdersDTO;
import com.GorgeousGlam.exception.OrderException;
import com.GorgeousGlam.exception.ProductNotFoundException;
import com.GorgeousGlam.model.Address;
import com.GorgeousGlam.model.Cart;
import com.GorgeousGlam.model.Customer;
import com.GorgeousGlam.model.Orders;
import com.GorgeousGlam.model.Product;
import com.GorgeousGlam.repository.OrderRepo;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private ICartService cartService;

	@Autowired
	private ICustomerService customerService;

	@Override
	public OrdersDTO addOrder(Orders orders, Integer customerId) throws OrderException {

		Customer customer = customerService.getCustomerDetailsById(customerId);
		Cart cart = cartService.viewCartbyId(customerId);
		orders.setCart(cart);
		orders.setCustomer(customer);
		orders.setOrderStatus("Placed");

		Map<Product, Integer> products = cart.getProducts();
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No product found in cart..");
		}
		double totalCost = 0;
		int totalQt = 0;

		Set<Product> productSet = products.keySet();
		for (Product product : productSet) {
			totalCost += product.getProductPrice() * product.getProductQuantity();
			totalQt += product.getProductQuantity();
		}

		orders.setTotalQuantity(totalQt);
		orders.setTotalCost(totalCost);

		orders.setOrderDateTime(LocalDateTime.now());

		Orders saveOrder = orderRepo.save(orders);

		if (saveOrder == null) {
			throw new OrderException("Please first add product to the Order...");
		}

		Map<Product, Integer> orderedProducts = cartService
				.emptyCart(customerService.getCustomerDetailsById(customerId).getCart().getCartId());

		OrdersDTO orderInfo = new OrdersDTO(saveOrder.getBookingOrderId(), saveOrder.getTransactionMode(), totalCost,
				totalQt, saveOrder.getOrderDateTime(), customer.getName(), customer.getEmail());

//		Address address = customer.getAddress();
//
//		AddressDTO deliveryAddress = new AddressDTO(address.getHouseNo(), address.getColony(), address.getCity(),
//				address.getState(), address.getPinCode());
//
//		orderInfo.setAddress(deliveryAddress);

		orderInfo.setProducts(orderedProducts);

		return orderInfo;

	}

	@Override
	public List<Orders> viewAllOrders() throws OrderException {

		List<Orders> allOrders = orderRepo.findAll();

		if (allOrders.isEmpty())
			throw new OrderException("No order found...");

		return allOrders;

	}

	@Override
	public Orders viewOrderById(Integer orderId) throws OrderException {

		return orderRepo.findById(orderId).orElseThrow(() -> new OrderException("No order found by id: " + orderId));

	}

	@Override
	public Orders deleteOrdrerById(Integer orderId) throws OrderException {

		Orders order = orderRepo.findById(orderId)
				.orElseThrow(() -> new OrderException("No order found by id: " + orderId));

		orderRepo.delete(order);

		return order;
	}

}
