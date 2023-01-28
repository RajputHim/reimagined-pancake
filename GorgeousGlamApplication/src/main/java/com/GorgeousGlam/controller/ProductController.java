package com.GorgeousGlam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.GorgeousGlam.model.Product;
import com.GorgeousGlam.service.IProductService;

@RestController
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<Product> addProductHandler(@RequestBody Product product) {

		Product savedProduct = productService.addProduct(product);

		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);

	}

}
