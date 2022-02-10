package com.chana.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.chana.exceptions.LoginException;
import com.chana.repositories.CompanyRepository;
import com.chana.repositories.CouponRepository;
import com.chana.repositories.CustomerRepository;

/**
 * class to other extends classes. (admin, customer and company) to eralize the login method.
 * @author Chana Kurtz
 *
 */
@Service
public abstract class ClientService {
	
	protected CompanyRepository companyRepository;
	protected CustomerRepository customerRepository;
	protected CouponRepository couponRepository;
	

	public ClientService(CompanyRepository companyRepository, CustomerRepository customerRepository,
			CouponRepository couponRepository) {
		this.companyRepository = companyRepository;
		this.customerRepository = customerRepository;
		this.couponRepository = couponRepository;
	}

	public ClientService() {
	}



	/**
	 * login method
	 * 
	 * @param email    is the user's email address
	 * @param password is the user's password
	 * @return true of false
	 * @throws ServiceException if the details are incorrect.
	 * @throws LoginException 
	 */

	public abstract boolean login(String email, String password) throws LoginException;

	

}
