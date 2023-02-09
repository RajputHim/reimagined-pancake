package com.GorgeousGlam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GorgeousGlam.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
