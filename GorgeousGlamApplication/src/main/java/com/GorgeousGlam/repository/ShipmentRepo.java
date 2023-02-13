package com.GorgeousGlam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GorgeousGlam.model.Shipment;

public interface ShipmentRepo extends JpaRepository<Shipment, Integer> {

}
