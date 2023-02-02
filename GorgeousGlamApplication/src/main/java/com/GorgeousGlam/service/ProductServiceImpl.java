package com.GorgeousGlam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GorgeousGlam.exception.ProductNotFoundException;
import com.GorgeousGlam.model.Product;
import com.GorgeousGlam.repository.ProductRepo;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepo productRepo;

	@Override
	public Product addProduct(Product product) {

		Product savedProduct = productRepo.save(product);

		if (savedProduct == null) {
			throw new ProductNotFoundException("Product not saved..");
		}

		return savedProduct;

	}

	@Override
	public Product getProductById(Integer pId) throws ProductNotFoundException {
		Optional<Product> productOpt = productRepo.findById(pId);

		if (productOpt.isEmpty()) {
			throw new ProductNotFoundException("No product found by id: " + pId);
		} else {
			return productOpt.get();
		}
	}

	@Override
	public Product deleteProductById(Integer pId) throws ProductNotFoundException {
		Optional<Product> productOpt = productRepo.findById(pId);

		if (productOpt.isEmpty()) {
			throw new ProductNotFoundException("No product found by id: " + pId);
		} else {
			Product product = productOpt.get();
			productRepo.deleteById(pId);
			return product;
		}
	}

	@Override
	public List<Product> getAllProduct() throws ProductNotFoundException {
		List<Product> allProducts = productRepo.findAll();
		if (allProducts.isEmpty()) {
			throw new ProductNotFoundException("No products found..");
		}

		return allProducts;

	}

}
