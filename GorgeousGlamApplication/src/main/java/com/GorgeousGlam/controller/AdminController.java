package com.GorgeousGlam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.GorgeousGlam.DTO.AdminDTO;
import com.GorgeousGlam.model.Admin;
import com.GorgeousGlam.service.IAdminService;

@RestController
public class AdminController {

	@Autowired
	private IAdminService adminService;

	@PostMapping("/admins")
	public ResponseEntity<Admin> addAdminHandler(@RequestBody AdminDTO admin) {

		Admin savedAdmin = adminService.addAdmin(admin);

		return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);

	}

	@DeleteMapping("/admins/id/{aId}")
	public ResponseEntity<Admin> deleteAdminByIdHandler(@PathVariable("aId") Integer aId) {

		Admin deletedAdmin = adminService.deleteAdminById(aId);

		return new ResponseEntity<>(deletedAdmin, HttpStatus.ACCEPTED);

	}

	@PatchMapping("/admins")
	public ResponseEntity<Admin> updateAdminDetailsHandler(@RequestBody Admin admin) {

		Admin updatedAdmin = adminService.updateAdminDetails(admin);

		return new ResponseEntity<>(updatedAdmin, HttpStatus.ACCEPTED);

	}

	@GetMapping("/admins/id/{aId}")
	public ResponseEntity<Admin> viewAdminDetailsHandler(@PathVariable("aId") Integer aId) {

		Admin admin = adminService.getAdminDetailsById(aId);

		return new ResponseEntity<>(admin, HttpStatus.OK);

	}

}
