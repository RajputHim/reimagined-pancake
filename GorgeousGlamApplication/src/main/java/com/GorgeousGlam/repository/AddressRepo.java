package com.GorgeousGlam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GorgeousGlam.model.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
