package com.GorgeousGlam.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.GorgeousGlam.exception.CustomerException;
import com.GorgeousGlam.exception.SessionException;
import com.GorgeousGlam.model.Cart;
import com.GorgeousGlam.model.Orders;
import com.GorgeousGlam.model.Product;
import com.GorgeousGlam.model.Session;
import com.GorgeousGlam.model.UserType;
import com.GorgeousGlam.service.ICartService;
import com.GorgeousGlam.service.ICustomerService;
import com.GorgeousGlam.service.IOrderService;
import com.GorgeousGlam.service.IProductService;
import com.GorgeousGlam.service.ISessionService;

@RestController
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@Autowired
	private ISessionService sessionService;

	@Autowired
	private IProductService productService;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private ICartService cartService;

	@PostMapping(value = "/orders/id/{customerId}/{cartId}")
	public ResponseEntity<Orders> addOrdersHandler(@Valid @RequestBody Orders order,
			@PathVariable("customerId") Integer customerId, @RequestParam("sessionKey") String sessionKey,
			@PathVariable("cartId") Integer cartId) throws SessionException, CustomerException {

		Session session = sessionService.getSessionByKey(sessionKey);
		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			Orders placedOrder = orderService.addOrder(order, cartId, customerId);

			return new ResponseEntity<Orders>(placedOrder, HttpStatus.CREATED);

		} else {
			throw new SessionException("Please login with correct credentials");
		}
	}

	@GetMapping("/orders/id/{userId}")
	public ResponseEntity<Orders> viewOrderByIdHandler(@PathVariable("userId") Integer userId,
			@RequestParam("sessionKey") String sessionKey) {

		Session session = sessionService.getSessionByKey(sessionKey);
		if (session.getUserId() == userId && session.getUserType() == UserType.CUSTOMER) {

			Orders orders = orderService.viewOrderById(userId);

			return new ResponseEntity<Orders>(orders, HttpStatus.OK);

		} else {
			throw new SessionException("Please login with correct credentials");
		}

	}

	@GetMapping("/orders/{customerId}")
	public ResponseEntity<List<Orders>> viewAllOrderHandler(@PathVariable("customerId") Integer customerId,
			@RequestParam("sessionKey") String sessionKey) {

		Session session = sessionService.getSessionByKey(sessionKey);
		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			List<Orders> allOrders = orderService.viewAllOrders();

			return new ResponseEntity<List<Orders>>(allOrders, HttpStatus.OK);

		} else {
			throw new SessionException("Please login with correct credentials");
		}

	}

	@DeleteMapping("/orders/{orderId}/{customerId}")
	public ResponseEntity<Orders> deleteOrderByIdHandler(@PathVariable("orderId") Integer orderId,
			@PathVariable("customerId") Integer customerId, @RequestParam("sessionKey") String sessionKey) {

		Session session = sessionService.getSessionByKey(sessionKey);
		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			Orders deletedOrders = orderService.deleteOrdrerById(orderId);

			return new ResponseEntity<Orders>(deletedOrders, HttpStatus.OK);

		} else {
			throw new SessionException("Please login with correct credentials");
		}

	}

}
