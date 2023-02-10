package com.GorgeousGlam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

	@PostMapping("/carts/products/{productId}/{quantity}")
	public ResponseEntity<Cart> addProductToCartHandler(@RequestParam("userId") Integer customerId,
			@RequestParam("sessionKey") String sessionKey, @PathVariable("productId") Integer productId,
			@PathVariable("quantity") Integer quantity)
			throws SessionException, ProductNotFoundException, CustomerException {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session == null) {
			throw new SessionException("Please login to your account first..");
		}

		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			Cart updatedCart = cartService.addProductToCart(productId, customerId, quantity);

			return new ResponseEntity<Cart>(updatedCart, HttpStatus.ACCEPTED);

		} else {
			throw new SessionException("Please login with the correct credentials");
		}
	}

	@GetMapping("/carts")
	public ResponseEntity<Cart> viewCartByIdHandler(@RequestParam("userId") Integer customerId,
			@RequestParam("sessionKey") String sessionKey) throws CustomerException, SessionException {
		Session session = sessionService.getSessionByKey(sessionKey);

		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			Cart cart = cartService.viewCartbyId(customerId);

			return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);

		} else {
			throw new SessionException("Please login with the correct credentials");
		}
	}

	@DeleteMapping("/carts/{productId}")
	public ResponseEntity<Cart> deleteProductFromCartHandler(@PathVariable("productId") Integer productId,
			@RequestParam("userId") Integer customerId, @RequestParam("sessionKey") String sessionKey)
			throws CustomerException, SessionException {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			Cart cart = cartService.deleteProductFromCart(productId, customerId);

			return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);

		} else {
			throw new SessionException("You are not logged in..");
		}
	}

	@PatchMapping("/carts/{productId}/{newQuantity}")
	public ResponseEntity<Cart> updateProductQuantityHandler(@PathVariable("productId") Integer productId,
			@PathVariable("newQuantity") Integer newQuantity, @RequestParam("userId") Integer customerId,
			@RequestParam("sessionKey") String sessionKey) throws CustomerException, SessionException {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			Cart cart = cartService.changeProductQuantity(productId, customerId, newQuantity);

			return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);

		} else {
			throw new SessionException("You are not logged in..");
		}
	}

}
