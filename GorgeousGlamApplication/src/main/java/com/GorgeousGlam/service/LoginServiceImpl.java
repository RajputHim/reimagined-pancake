package com.GorgeousGlam.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GorgeousGlam.DTO.LoginDTO;
import com.GorgeousGlam.exception.AdminException;
import com.GorgeousGlam.exception.CustomerException;
import com.GorgeousGlam.model.Admin;
import com.GorgeousGlam.model.Customer;
import com.GorgeousGlam.model.Session;
import com.GorgeousGlam.model.UserType;
import com.GorgeousGlam.repository.AdminRepo;
import com.GorgeousGlam.repository.CustomerRepo;
import com.GorgeousGlam.repository.SessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private SessionRepo sessionRepo;

	@Override
	public Session login(LoginDTO loginDto) throws LoginException, AdminException {

		Session currSession = null;

		if (loginDto.getUserType() == UserType.ADMIN) {
			Admin admin = adminRepo.findByEmail(loginDto.getEmail());

			if (admin == null) {
				throw new AdminException("No admin found by email: " + loginDto.getEmail());
			}

			Integer userId = admin.getAdminId();

			Optional<Session> opt = sessionRepo.findById(userId);

			if (opt.isPresent()) {
				throw new LoginException("Admin already logged in");
			}

			if (!admin.getPassword().equals(loginDto.getPassword())) {
				throw new LoginException("Incorrect password..");
			}

			String key = RandomString.make(6);

			Session newSession = new Session();

			newSession.setSessionKey(key);
			newSession.setTimeStamp(LocalDateTime.now());
			newSession.setUserId(userId);
			newSession.setUserType(UserType.ADMIN);

			currSession = sessionRepo.save(newSession);

		} else if (loginDto.getUserType() == UserType.CUSTOMER) {
			Customer customer = customerRepo.findByEmail(loginDto.getEmail());

			if (customer == null) {
				throw new CustomerException("No customer found by email: " + loginDto.getEmail());
			}

			Integer userId = customer.getCustomerId();

			Optional<Session> opt = sessionRepo.findById(userId);

			if (opt.isPresent()) {
				throw new LoginException("Customer already logged in");
			}

			if (!customer.getPassword().equals(loginDto.getPassword())) {
				throw new LoginException("Incorrect password..");
			}

			String key = RandomString.make(6);

			Session newSession = new Session();

			newSession.setSessionKey(key);
			newSession.setTimeStamp(LocalDateTime.now());
			newSession.setUserId(userId);
			newSession.setUserType(UserType.CUSTOMER);

			currSession = sessionRepo.save(newSession);
		}

		return currSession;
	}

	@Override
	public String logout(Integer userId) throws LoginException {
		Session session = sessionRepo.findById(userId)
				.orElseThrow(() -> new LoginException("No user is logged in with userId: " + userId));
		sessionRepo.delete(session);
		return "User logged out successfully";
	}

}
