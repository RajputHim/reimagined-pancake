package com.GorgeousGlam.service;

import java.util.ArrayList;
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
	public List<Product> addProduct(List<Product> products) {
		List<Product> savedProducts = new ArrayList<>();
		boolean saved = false;
		for (Product product : products) {
			List<Product> existingProducts = productRepo.findByCategory(product.getCategory());
			saved = false;
			for (Product p : existingProducts) {
				if (p.equals(product)) {
					Product existingProduct = productRepo.findById(p.getProductId()).orElseThrow(
							() -> new ProductNotFoundException("No product found by id: " + p.getProductId()));
					existingProduct
							.setProductQuantity(existingProduct.getProductQuantity() + product.getProductQuantity());

					Product savedProduct = productRepo.save(existingProduct);
					savedProducts.add(savedProduct);
					saved = true;
					break;
				}
			}
			if (!saved) {
				Product savedProduct = productRepo.save(product);
				savedProducts.add(savedProduct);
			}
		}

		return savedProducts;

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

//	@Override
//	public List<Product> addMultipleProducts(List<Product> products) {
//		List<Product> savedProducts = productRepo.saveAll(products);
//
//		if (savedProducts.isEmpty()) {
//			throw new ProductNotFoundException("Products not saved..");
//		}
//
//		return savedProducts;
//	}

}
