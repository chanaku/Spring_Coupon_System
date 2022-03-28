package com.chana.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chana.beans.Category;
import com.chana.beans.Company;
import com.chana.beans.Coupon;
import com.chana.beans.Customer;
import com.chana.repositories.CompanyRepository;
import com.chana.repositories.CouponRepository;
import com.chana.repositories.CustomerRepository;
@Service
public class GuestService {
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CouponRepository couponRepository;
	
	
	/**
	 * get all companies method
	 * @return all the companies from DB.
	 */
	public ArrayList<Company> getAllCompany() {
		return companyRepository.findAll();
	}
	
	/**
	 * get all coupons method
	 * 
	 * @return the all coupons existing in DB.
	 */
	
	public ArrayList<Coupon> getAllCoupons(){
		return couponRepository.findAll();
	}
	
	public ArrayList<Coupon> getAllCouponsByCategory(Category category){
		return couponRepository.findByCategory(category);
	}
	
}
