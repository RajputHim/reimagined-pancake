package com.GorgeousGlam.service;

import com.GorgeousGlam.exception.CustomerException;
import com.GorgeousGlam.exception.ProductNotFoundException;
import com.GorgeousGlam.model.Cart;

public interface ICartService {

	public Cart addProductToCart(Integer product_Id, Integer customerId)
			throws ProductNotFoundException, CustomerException;

//	public ProductCart addPlantToCart(Integer plantId, Integer customerId) throws PlantException, CustomerException;
//
//	public ProductCart addSeedToCart(Integer seedId, Integer customerId) throws SeedException, CustomerException;
//
	public Cart viewCartbyId(Integer cartId, Integer customerId) throws CustomerException;
//	
}
