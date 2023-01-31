package com.GorgeousGlam.controller;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.GorgeousGlam.DTO.LoginDTO;
import com.GorgeousGlam.exception.AdminException;
import com.GorgeousGlam.model.Session;
import com.GorgeousGlam.service.ILoginService;

@RestController
public class LoginController {
	
	@Autowired
	private ILoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<Session> loginHandler(@RequestBody LoginDTO loginDto) throws LoginException, AdminException {
		Session session = loginService.login(loginDto);
		return new ResponseEntity<>(session, HttpStatus.ACCEPTED);
	}

	@PostMapping("/logout/{userId}")
	public ResponseEntity<String> logoutHandler(@PathVariable("userId") Integer userId) throws LoginException {
		String message = loginService.logout(userId);
		return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
	}
}
