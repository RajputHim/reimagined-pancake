package com.GorgeousGlam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GorgeousGlam.DTO.AdminDTO;
import com.GorgeousGlam.exception.AdminException;
import com.GorgeousGlam.model.Admin;
import com.GorgeousGlam.repository.AdminRepo;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public Admin addAdmin(AdminDTO admin) throws AdminException {

		Admin existingAdmin = adminRepo.findByEmail(admin.getEmail());

		if (existingAdmin != null) {

			throw new AdminException("Admin already exists with email: " + admin.getEmail());

		} else {

			Admin newAdmin = new Admin();

			newAdmin.setEmail(admin.getEmail());
			newAdmin.setName(admin.getName());
			newAdmin.setPassword(admin.getPassword());

			Admin savedAdmin = adminRepo.save(newAdmin);

			if (savedAdmin == null) {
				throw new AdminException("Admin not saved..");
			}

			return savedAdmin;

		}

	}

	@Override
	public Admin getAdminDetailsById(Integer aId) throws AdminException {
		return adminRepo.findById(aId).orElseThrow(() -> new AdminException("No admin found by id: " + aId));
	}

	@Override
	public Admin updateAdminDetails(Admin admin) throws AdminException {
		Admin existingAdmin = adminRepo.findById(admin.getAdminId())
				.orElseThrow(() -> new AdminException("No admin found by id: " + admin.getAdminId()));

		if (admin.getEmail() != null) {
			existingAdmin.setEmail(admin.getEmail());
		}

		if (admin.getName() != null) {
			existingAdmin.setName(admin.getName());
		}

		if (admin.getPassword() != null) {
			existingAdmin.setPassword(admin.getPassword());
		}

		existingAdmin = adminRepo.save(existingAdmin);

		return existingAdmin;
	}

	@Override
	public Admin deleteAdminById(Integer aId) throws AdminException {

		Admin admin = adminRepo.findById(aId).orElseThrow(() -> new AdminException("No admin found by id: " + aId));

		adminRepo.delete(admin);
		return admin;

	}

}
