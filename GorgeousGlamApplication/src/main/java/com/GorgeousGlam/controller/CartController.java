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

	@PostMapping("/carts/products/{product_Id}")
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

	@GetMapping("/carts/{cartId}")
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

	@DeleteMapping("/carts/{product_Id}")
	public ResponseEntity<Cart> deleteProductFromCartHandler(@PathVariable("product_Id") Integer product_Id,
			@RequestParam("customerId") Integer customerId, @RequestParam("sessionKey") String sessionKey)
			throws CustomerException, SessionException {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			Cart cart = cartService.deleteProductFromCart(product_Id, customerId);

			return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);

		} else {
			throw new SessionException("You are not logged in..");
		}
	}

	@PatchMapping("/carts/{product_Id}/{newQuantity}")
	public ResponseEntity<Cart> updateProductQuantityHandler(@PathVariable("product_Id") Integer product_Id,
			@PathVariable("newQuantity") Integer newQuantity, @RequestParam("customerId") Integer customerId,
			@RequestParam("sessionKey") String sessionKey) throws CustomerException, SessionException {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session.getUserId() == customerId && session.getUserType() == UserType.CUSTOMER) {

			Cart cart = cartService.changeProductQuantity(product_Id, customerId, newQuantity);

			return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);

		} else {
			throw new SessionException("You are not logged in..");
		}
	}

}
