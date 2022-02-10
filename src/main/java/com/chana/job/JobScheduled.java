package com.chana.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.chana.repositories.CouponRepository;

@Component
public class JobScheduled {
	
	@Autowired
	private CouponRepository couponRepository;

	/**
	 * a variable that contains the time of 24 hours.
	 */
	public static final long twentyFourHours = 1000 * 60 * 60 * 24;

	/**
	 * this method id async on the system, and running each 24 hourse
	 * to delete all the expired coupons.
	 */
	@Scheduled(fixedRate = twentyFourHours) // can also use 'cron'
	public void run() {
		couponRepository.deleteExpiredCoupons();
	}

}
