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

		/*
		 * This method will add the products into the database, and the return the
		 * updated product list.
		 */

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

		List<Product> allProducts = productRepo.findAll();

		return allProducts;

	}

	@Override
	public Product getProductById(Integer pId) throws ProductNotFoundException {

		/*
		 * This method will return the product data by fetching the data from database
		 * through product id.
		 */

		Optional<Product> productOpt = productRepo.findById(pId);

		if (productOpt.isEmpty()) {
			throw new ProductNotFoundException("No product found by id: " + pId);
		} else {
			return productOpt.get();
		}
	}

	@Override
	public Product deleteProductById(Integer pId) throws ProductNotFoundException {

		/*
		 * This method will delete the product data by fetching the data from database
		 * through product id, and return the delete product details.
		 */

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

		/*
		 * This method will return the list of products data.
		 */

		List<Product> allProducts = productRepo.findAll();
		if (allProducts.isEmpty()) {
			throw new ProductNotFoundException("No products found..");
		}

		return allProducts;

	}

	@Override
	public List<Product> getAllProductByCategory(String category) throws ProductNotFoundException {

		/*
		 * This method will fetch and return the list of products belonging to
		 * particular category.
		 */

		List<Product> products = productRepo.findByCategory(category);

		if (products.isEmpty()) {
			throw new ProductNotFoundException("No product found in this category..");
		}

		return products;
	}

	@Override
	public List<Product> getAllProductByProductType(String productType) throws ProductNotFoundException {

		/*
		 * This method will return all the products by fetching the details through
		 * product type.
		 */

		List<Product> products = productRepo.findByProductType(productType);

		if (products.isEmpty()) {
			throw new ProductNotFoundException("No product found by this type ..");
		}
		return products;
	}

	@Override
	public List<Product> getAllProductByPriceRange(Double startPrice, Double endPrice) throws ProductNotFoundException {

		/*
		 * This method will return all the products between the a price range.
		 */

		List<Product> products = productRepo.findByProductPriceBetween(startPrice, endPrice);

		if (products.isEmpty()) {
			throw new ProductNotFoundException("No product found between this price range..");
		}
		return products;
	}

}
