package com.GorgeousGlam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GorgeousGlam.exception.ProductNotFoundException;
import com.GorgeousGlam.model.Product;
import com.GorgeousGlam.repository.ProductRepo;

@Service
public class IProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepo productRepo;

	@Override
	public Product addProduct(Product product) {

		if (product != null) {
			Product savedProduct = productRepo.save(product);
			return savedProduct;
		} else {
			throw new ProductNotFoundException("Please provide correct details");
		}

	}

	@Override
	public Product getProductById(Integer pId) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
