package com.GorgeousGlam.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.GorgeousGlam.exception.SessionException;
import com.GorgeousGlam.model.Product;
import com.GorgeousGlam.model.Session;
import com.GorgeousGlam.model.UserType;
import com.GorgeousGlam.service.IProductService;
import com.GorgeousGlam.service.ISessionService;

@RestController
public class ProductController {

	@Autowired
	private IProductService productService;

	@Autowired
	private ISessionService sessionService;
//
//	@PostMapping("/products/{adminId}")
//	public ResponseEntity<Product> addProductHandler(@PathVariable("adminId") Integer adminId,
//			@RequestParam("sessionKey") String sessionKey, @Valid @RequestBody Product product) {
//
//		Session session = sessionService.getSessionByKey(sessionKey);
//
//		if (session.getUserId() == adminId && session.getUserType() == UserType.ADMIN) {
//			Product savedProduct = productService.addProduct(product);
//
//			return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
//		} else {
//			throw new SessionException("Please login before adding product..");
//		}
//
//	}

	@PostMapping("/Products/{adminId}")
	public ResponseEntity<List<Product>> addProductHandler(@PathVariable("adminId") Integer adminId,
			@RequestParam("sessionKey") String sessionKey, @Valid @RequestBody List<Product> products) {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session.getUserId() == adminId && session.getUserType() == UserType.ADMIN) {
			List<Product> savedProducts = productService.addProduct(products);

			return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
		} else {
			throw new SessionException("Please login before adding product..");
		}

	}

	@GetMapping("/products/id/{pId}")
	public ResponseEntity<Product> viewProductByIdHandler(@PathVariable("pId") Integer pId) {

		Product product = productService.getProductById(pId);

		return new ResponseEntity<>(product, HttpStatus.OK);

	}

	@DeleteMapping("/products/{pId}/{adminId}")
	public ResponseEntity<Product> deleteProductByIdHandler(@PathVariable("pId") Integer pId,
			@PathVariable("adminId") Integer adminId, @RequestParam("sessionKey") String sessionKey) {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session.getUserId() == adminId && session.getUserType() == UserType.ADMIN) {

			Product deletedProduct = productService.deleteProductById(pId);

			return new ResponseEntity<>(deletedProduct, HttpStatus.ACCEPTED);

		} else {
			throw new SessionException("Please login before deleting product..");
		}

	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> viewAllProductHandler() {

		List<Product> allProduct = productService.getAllProduct();

		return new ResponseEntity<>(allProduct, HttpStatus.OK);

	}

	@GetMapping("/products/{category}")
	public ResponseEntity<List<Product>> viewAllProductByCategoryHandler(@PathVariable("category") String category) {

		List<Product> allProduct = productService.getAllProductByCategory(category);

		return new ResponseEntity<>(allProduct, HttpStatus.OK);

	}

	@GetMapping("/products/type/{productType}")
	public ResponseEntity<List<Product>> viewAllProductByProductTypeHandler(
			@PathVariable("productType") String productType) {

		List<Product> allProduct = productService.getAllProductByProductType(productType);

		return new ResponseEntity<>(allProduct, HttpStatus.OK);

	}

	@GetMapping("/products/{startPrice}/{endPrice}")
	public ResponseEntity<List<Product>> viewAllProductByPriceRangeHandler(
			@PathVariable("startPrice") Double startPrice, @PathVariable("endPrice") Double endPrice) {

		List<Product> allProduct = productService.getAllProductByPriceRange(startPrice, endPrice);

		return new ResponseEntity<>(allProduct, HttpStatus.OK);

	}

}
