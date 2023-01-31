package com.GorgeousGlam.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Session {

	@Id
	private Integer userId;

	private LocalDateTime timeStamp;

	private String sessionKey;

	private UserType userType;

}
