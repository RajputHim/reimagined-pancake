package com.GorgeousGlam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.GorgeousGlam.exception.CustomerException;
import com.GorgeousGlam.exception.ProductNotFoundException;
import com.GorgeousGlam.exception.SessionException;
import com.GorgeousGlam.model.Cart;
import com.GorgeousGlam.model.Session;
import com.GorgeousGlam.model.UserType;
import com.GorgeousGlam.service.ICartService;
import com.GorgeousGlam.service.ISessionService;

@RestController
public class CartController {

	@Autowired
	private ICartService cartService;

	@Autowired
	private ISessionService sessionService;

	@PostMapping("/cart/products/{product_Id}")
	public ResponseEntity<Cart> addProductToCartHandler(@RequestParam("customerId") Integer customerId,
			@RequestParam("sessionKey") String sessionKey, @PathVariable("product_Id") Integer product_Id)
			throws SessionException, ProductNotFoundException, CustomerException {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session == null) {
			throw new SessionException("Please login to your account first..");
		}

		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			Cart updatedCart = cartService.addProductToCart(product_Id, customerId);

			return new ResponseEntity<Cart>(updatedCart, HttpStatus.ACCEPTED);

		} else {
			throw new SessionException("Please login with the correct credentials");
		}
	}

	@GetMapping("/cart/{cartId}")
	public ResponseEntity<Cart> viewCartByIdHandler(@PathVariable("cartId") Integer cartId,
			@RequestParam("customerId") Integer customerId, @RequestParam("sessionKey") String sessionKey)
			throws CustomerException, SessionException {
		Session session = sessionService.getSessionByKey(sessionKey);

		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			Cart cart = cartService.viewCartbyId(cartId, customerId);

			return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);

		} else {
			throw new SessionException("Please login with the correct credentials");
		}
	}
}
