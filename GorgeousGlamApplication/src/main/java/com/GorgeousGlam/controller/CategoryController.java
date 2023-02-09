package com.GorgeousGlam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.GorgeousGlam.model.Category;
import com.GorgeousGlam.service.ICategoryService;

@RestController
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@PostMapping("/categories")
	public ResponseEntity<Category> addCategoryHandler(@RequestBody Category category,
			@RequestParam String sessionKey) {

		Category savedCategory = categoryService.addCategory(category);

		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}

}
