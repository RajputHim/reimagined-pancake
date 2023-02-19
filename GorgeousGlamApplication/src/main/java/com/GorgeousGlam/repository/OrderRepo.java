package com.GorgeousGlam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.GorgeousGlam.model.Orders;

public interface OrderRepo extends JpaRepository<Orders, Integer> {

//	@Query("SELECT o FROM Orders o WHERE o.customerId=?1")
//	public List<Orders> getAllOrders(Integer customerId);

}
