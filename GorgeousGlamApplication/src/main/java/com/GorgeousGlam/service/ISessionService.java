package com.GorgeousGlam.service;

import com.GorgeousGlam.exception.SessionException;
import com.GorgeousGlam.model.Session;

public interface ISessionService {

	public Session getSessionByKey(String key) throws SessionException;
}
