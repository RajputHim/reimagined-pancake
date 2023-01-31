package com.GorgeousGlam.service;

import javax.security.auth.login.LoginException;

import com.GorgeousGlam.DTO.LoginDTO;
import com.GorgeousGlam.exception.AdminException;
import com.GorgeousGlam.model.Session;

public interface ILoginService {
	public Session login(LoginDTO loginDto) throws LoginException, AdminException;

	public String logout(Integer userId) throws LoginException;
}
