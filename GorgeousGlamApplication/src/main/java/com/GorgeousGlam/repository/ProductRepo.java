package com.GorgeousGlam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GorgeousGlam.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	public List<Product> findByCategory(String category);

	public List<Product> findByProductType(String productType);

	public List<Product> findByProductPriceBetween(Double startPrice, Double endPrice);

}
