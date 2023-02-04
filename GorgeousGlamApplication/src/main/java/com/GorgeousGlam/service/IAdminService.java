package com.GorgeousGlam.service;

import org.springframework.web.bind.MethodArgumentNotValidException;

import com.GorgeousGlam.DTO.AdminDTO;
import com.GorgeousGlam.exception.AdminException;
import com.GorgeousGlam.model.Admin;

public interface IAdminService {

	public Admin addAdmin(AdminDTO admin) throws AdminException, MethodArgumentNotValidException;

	public Admin getAdminDetailsById(Integer aId) throws AdminException;

	public Admin updateAdminDetails(Admin admin) throws AdminException;

	public Admin deleteAdminById(Integer aId) throws AdminException;

}
