package com.GorgeousGlam.controller;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.GorgeousGlam.DTO.AdminDTO;
import com.GorgeousGlam.exception.AdminException;
import com.GorgeousGlam.exception.SessionException;
import com.GorgeousGlam.model.Admin;
import com.GorgeousGlam.model.Session;
import com.GorgeousGlam.model.UserType;
import com.GorgeousGlam.service.IAdminService;
import com.GorgeousGlam.service.ISessionService;

@RestController
public class AdminController {

	@Autowired
	private IAdminService adminService;

	@Autowired
	private ISessionService sessionService;

	@PostMapping("/admins")
	public ResponseEntity<Admin> addAdminHandler(@Valid @RequestBody AdminDTO admin) throws AdminException {

		Admin savedAdmin = adminService.addAdmin(admin);

		return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);

	}

	@DeleteMapping("/admins/{aId}")
	public ResponseEntity<Admin> deleteAdminByIdHandler(@PathVariable("aId") Integer aId,
			@RequestParam("sessionKey") String sessionKey) throws AdminException, LoginException {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session == null) {
			throw new SessionException("Please login first..");
		}

		if (session.getUserId() == aId && (session.getUserType() == UserType.ADMIN)) {

			Admin deletedAdmin = adminService.deleteAdminById(aId);

			return new ResponseEntity<>(deletedAdmin, HttpStatus.ACCEPTED);
		} else {
			throw new AdminException("No admin found..");
		}

	}

	@PatchMapping("/admins")
	public ResponseEntity<Admin> updateAdminDetailsHandler(@RequestParam("sessionKey") String sessionKey,
			@Valid @RequestBody Admin admin) {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session == null) {
			throw new SessionException("Please login first..");
		}

		if (session.getUserId() == admin.getAdminId() && (session.getUserType() == UserType.ADMIN)) {

			Admin updatedAdmin = adminService.updateAdminDetails(admin);

			return new ResponseEntity<>(updatedAdmin, HttpStatus.ACCEPTED);
		} else {
			throw new AdminException("No admin found..");
		}

	}

	@GetMapping("/admins/{aId}")
	public ResponseEntity<Admin> viewAdminDetailsHandler(@PathVariable("aId") Integer aId,
			@RequestParam("sessionKey") String sessionKey) {

		Session session = sessionService.getSessionByKey(sessionKey);

		if (session == null) {
			throw new SessionException("Please login first..");
		}

		if (session.getUserId() == aId && (session.getUserType() == UserType.ADMIN)) {

			Admin admin = adminService.getAdminDetailsById(aId);

			return new ResponseEntity<>(admin, HttpStatus.OK);

		} else {
			throw new AdminException("No admin found..");
		}

	}

}
