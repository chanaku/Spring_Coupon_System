package com.chana.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.chana.login.TokenManager;

@Component
public class RemoveExpiredToken {
	@Autowired
	private TokenManager tokenManager;

	@Scheduled(fixedRate = 1000 * 60 * 4)
	public void run() {
		System.out.println("removed expired token is running...");
		tokenManager.removeExpired();
	}

}
