package com.GorgeousGlam.service;

import java.util.List;

import com.GorgeousGlam.exception.CartException;
import com.GorgeousGlam.exception.CustomerException;
import com.GorgeousGlam.exception.ProductNotFoundException;
import com.GorgeousGlam.model.Cart;
import com.GorgeousGlam.model.Product;

public interface ICartService {

	public Cart addProductToCart(Integer product_Id, Integer customerId, Integer quantity)
			throws ProductNotFoundException, CustomerException;

	public Cart viewCartbyId(Integer customerId) throws CustomerException, CartException;

	public Cart deleteProductFromCart(Integer product_Id, Integer customerId)
			throws ProductNotFoundException, CustomerException;

	public Cart changeProductQuantity(Integer product_Id, Integer customerId, Integer newQuantity)
			throws ProductNotFoundException, CustomerException;

	public List<Product> emptyCart(Integer cartId) throws CartException;
}
