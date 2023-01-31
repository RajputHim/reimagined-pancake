package com.GorgeousGlam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GorgeousGlam.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	public Customer findByEmail(String email);
}
