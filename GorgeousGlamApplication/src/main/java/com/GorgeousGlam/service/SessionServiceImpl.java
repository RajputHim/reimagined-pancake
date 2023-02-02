package com.GorgeousGlam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GorgeousGlam.exception.SessionException;
import com.GorgeousGlam.model.Session;
import com.GorgeousGlam.repository.SessionRepo;

@Service
public class SessionServiceImpl implements ISessionService {

	@Autowired
	private SessionRepo sessionRepo;

	@Override
	public Session getSessionByKey(String key) throws SessionException {
		Session currentSession = sessionRepo.findBySessionKey(key);
		if (currentSession == null)
			throw new SessionException("No session fouund with session key: " + key);

		return currentSession;
	}

}
