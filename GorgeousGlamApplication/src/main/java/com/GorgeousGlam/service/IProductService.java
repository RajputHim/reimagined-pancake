package com.GorgeousGlam.service;

import java.util.List;

import com.GorgeousGlam.exception.ProductNotFoundException;
import com.GorgeousGlam.model.Product;

public interface IProductService {

	public List<Product> addProduct(List<Product> products);

	public Product deleteProductById(Integer pId) throws ProductNotFoundException;

	public Product getProductById(Integer pId) throws ProductNotFoundException;

	public List<Product> getAllProduct() throws ProductNotFoundException;

	public List<Product> getAllProductByCategory(String category) throws ProductNotFoundException;

	public List<Product> getAllProductByProductType(String productType) throws ProductNotFoundException;

	public List<Product> getAllProductByPriceRange(Double startPrice, Double endPrice) throws ProductNotFoundException;

}
