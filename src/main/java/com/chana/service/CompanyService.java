package com.chana.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.chana.beans.Category;
import com.chana.beans.Company;
import com.chana.beans.Coupon;
import com.chana.exceptions.AddCouponException;
import com.chana.exceptions.LoginException;
import com.chana.exceptions.ServiceException;
import com.chana.repositories.CompanyRepository;
import com.chana.repositories.CouponRepository;
import com.chana.repositories.CustomerRepository;

@Service
@Scope("prototype")
public class CompanyService extends ClientService {
	
	
	public CompanyService(CompanyRepository companyRepository, CustomerRepository customerRepository,
			CouponRepository couponRepository) {
		super(companyRepository, customerRepository, couponRepository);
		// TODO Auto-generated constructor stub
	}
	private static int companyId=0;
		
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}



	/**
	 * login method - to register the system and use it.
	 * 
	 * @param email    - register with the email address
	 * @param password - is the user's the password.
	 * @throws service exception if the login details are incorrect.
	 * @return true if the login successes.
	 */
	@Override
	public boolean login(String email, String password) throws LoginException {
		if (companyRepository.existsByEmailAndPassword(email, password)) {
			companyId = companyRepository.findByEmailAndPassword(email, password).getId();
			return true;
		}
		throw new LoginException("company dosn't exist by email and password");
	}

	/**
	 * add coupon method
	 * 
	 * @param coupon    is the coupon to add to DB.
	 * @param companyId for checking if the coupon title already exist.
	 * @throws ServiceException if the title is existing.
	 * @throws AddCouponException 
	 */
	public void addCoupon(Coupon coupon, int companyId) throws AddCouponException {
		if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(),companyId )) {
			throw new AddCouponException("error while trying to add coupon, title already exist");
		} else {
			couponRepository.save(coupon);
			// no add exist title to other coupon of the same company
		}
	}

	/**
	 * update coupon method
	 * 
	 * @param coupon is the coupon details to update. note: the method can't verify
	 *               if the user try change the id.
	 */
	public void updateCoupon(Coupon coupon) {
		couponRepository.save(coupon);
	}

	/**
	 * delete coupon method
	 * 
	 * @param couponId is the coupon-id to delete.
	 */
	public void deleteCoupon(int couponId) {
		couponRepository.deleteById(couponId);
	}

	/**
	 * get company's coupons
	 * 
	 * @param companyId to select the coupons of this specific company
	 * @return coupons list of the company.
	 */
	public ArrayList<Coupon> getCompanyCoupons(int companyId) {
		return couponRepository.findByCompanyId(companyId);
	}

	/**
	 * get all coupons of the company
	 * 
	 * @param category
	 * @param companyId
	 * @return
	 */
	public List<Coupon> getCompanyCoupons(Category category, int companyId) {
		return couponRepository.findByCategoryAndCompanyId(category, companyId);
	}

	/**
	 * get company coupons by max price method.
	 * 
	 * @param maxPrice  to select the coupons by this.
	 * @param companyId to select the coupons by this.
	 * @return all company's coupons filtered by equal & less the max price
	 *         parameter.
	 */
	public ArrayList<Coupon> getCompanyCoupons(double maxPrice, int companyId) {
		return couponRepository.findByPriceLessThanEqualAndCompanyId(maxPrice, companyId);
	}

	/**
	 * get company details.
	 * 
	 * @param companyId is the id of the user that made login
	 * @return the company details.
	 */
	public Company getCompanyDetails(int companyId) {
		return companyRepository.findById(companyId);
	}

	/**
	 * get the company details by this params:
	 * 
	 * @param email
	 * @param password
	 * @return the company
	 */
	public Company getCompanyDetailsByEmailAndPassword(String email, String password) {
		System.out.println("this is email: " +email);
		System.out.println("this is password: " +password);
		return companyRepository.findByEmailAndPassword(email, password);
	}

	public Coupon getCouponById(int couponID) {
		return couponRepository.findById(couponID);
	}

	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}
}
