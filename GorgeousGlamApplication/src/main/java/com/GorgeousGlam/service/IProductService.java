package com.GorgeousGlam.service;

import java.util.List;

import com.GorgeousGlam.exception.ProductNotFoundException;
import com.GorgeousGlam.model.Product;

public interface IProductService {

	public Product addProduct(Product product);

	public List<Product> addMultipleProducts(List<Product> products);

	public Product deleteProductById(Integer pId) throws ProductNotFoundException;

	public Product getProductById(Integer pId) throws ProductNotFoundException;

	public List<Product> getAllProduct() throws ProductNotFoundException;

//	public Product getAllProductByCategory() throws ProductNotFoundException;
//	
//	public Product getAllProductByProductType() throws ProductNotFoundException;

}
