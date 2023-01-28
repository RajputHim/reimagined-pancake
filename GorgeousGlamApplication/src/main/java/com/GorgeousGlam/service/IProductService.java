package com.GorgeousGlam.service;

import com.GorgeousGlam.exception.ProductNotFoundException;
import com.GorgeousGlam.model.Product;

public interface IProductService {

	public Product addProduct(Product product);

	public Product getProductById(Integer pId) throws ProductNotFoundException;

}
