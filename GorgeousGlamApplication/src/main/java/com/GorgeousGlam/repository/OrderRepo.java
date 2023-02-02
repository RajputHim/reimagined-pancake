package com.GorgeousGlam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GorgeousGlam.model.Orders;

public interface OrderRepo extends JpaRepository<Orders, Integer> {

}
