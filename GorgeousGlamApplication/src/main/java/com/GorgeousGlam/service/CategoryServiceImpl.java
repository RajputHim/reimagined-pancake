package com.GorgeousGlam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GorgeousGlam.model.Category;
import com.GorgeousGlam.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category addCategory(Category category) {
		Category savedCategory = categoryRepo.save(category);

		return savedCategory;
	}

}
