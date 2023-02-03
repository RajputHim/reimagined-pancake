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

	@PostMapping("/products/{adminId}/{sessionKey}")
	public ResponseEntity<Product> addProductHandler(@PathVariable("adminId") Integer adminId,
			@PathVariable("sessionKey") String sessionKey, @Valid @RequestBody Product product) {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session.getUserId() == adminId && session.getUserType() == UserType.ADMIN) {
			Product savedProduct = productService.addProduct(product);

			return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
		} else {
			throw new SessionException("Please login before adding product..");
		}

	}

	@GetMapping("/products/id/{pId}")
	public ResponseEntity<Product> viewProductByIdHandler(@PathVariable("pId") Integer pId) {

		Product product = productService.getProductById(pId);

		return new ResponseEntity<>(product, HttpStatus.OK);

	}

	@DeleteMapping("/products/id/{pId}")
	public ResponseEntity<Product> deleteProductByIdHandler(@PathVariable("pId") Integer pId) {

		Product deletedProduct = productService.deleteProductById(pId);

		return new ResponseEntity<>(deletedProduct, HttpStatus.ACCEPTED);

	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> viewAllProductHandler() {

		List<Product> allProduct = productService.getAllProduct();

		return new ResponseEntity<>(allProduct, HttpStatus.OK);

	}

}
