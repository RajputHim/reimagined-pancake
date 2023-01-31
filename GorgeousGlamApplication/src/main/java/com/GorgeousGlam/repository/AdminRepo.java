package com.GorgeousGlam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GorgeousGlam.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

	public Admin findByEmail(String email);
}
