package com.GorgeousGlam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GorgeousGlam.model.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer>{

}
