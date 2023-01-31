package com.GorgeousGlam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GorgeousGlam.model.Session;

public interface SessionRepo extends JpaRepository<Session, Integer> {

	public Session findBySessionKey(String key);
}
