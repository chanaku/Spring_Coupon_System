package com.chana.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.chana.beans.Category;
import com.chana.beans.Coupon;
import com.chana.beans.Customer;
import com.chana.exceptions.LoginException;
import com.chana.exceptions.PurchaseCouponException;
import com.chana.exceptions.ServiceException;
import com.chana.repositories.CompanyRepository;
import com.chana.repositories.CouponRepository;
import com.chana.repositories.CustomerRepository;
import com.chana.utils.ClientType;

@Service
@Scope("prototype")
public class CustomerService extends ClientService {
	
	
	
	public CustomerService(CompanyRepository companyRepository, CustomerRepository customerRepository,
			CouponRepository couponRepository) {
		super(companyRepository, customerRepository, couponRepository);
	}
	

	private int customerId;
	

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * login method - to register the system and use it.
	 * 
	 * @param email    - register with the email address
	 * @param password - is the user's the password.
	 * @throws login error exception if the login details are incorrect.
	 * @return true if the login successes.
	 */
	@Override
	public boolean login(String email, String password) throws LoginException {
		if (customerRepository.existsByEmailAndPassword(email, password)) {
			Customer customer = customerRepository.findByEmailAndPassword(email, password);
			customerId = customer.getId();
			return true;
		}
		throw new LoginException("login error. email or password dosn't exist.");

	}

	/**
	 * method for adding customer to DB.
	 * 
	 * @param customer is the new customer's details
	 */
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	/**
	 * the method for new purchases coupon by customer.
	 * 
	 * @param coupon is the coupon for buying
	 * @exception ServiceException on input error:
	 * @return exception if customer have the coupon
	 * @return exception if coupon is expired
	 * @return exception if coupon's quantity is 0.
	 * @throws PurchaseCouponException 
	 * @see everithing is ok, the methos update the quantity of coupon 1 less, and
	 *      add for the customer the new coupon.
	 */
	public void purchaseCoupon(Coupon coupon) throws PurchaseCouponException {
		if (couponRepository.existsByIdAndAmountEquals(coupon.getId(), 0)) {
			throw new PurchaseCouponException("coupon out of stock.");
		}
		if (couponRepository.isCouponExpierd(coupon.getId())) {
			throw new PurchaseCouponException("coupon is expired.");
		}
		if (customerRepository.customerPurchesedCoupon(customerId, coupon.getId())) {
			throw new PurchaseCouponException("can buy only once.");
		}
		couponRepository.updateCouponQuantity(coupon.getId());
		couponRepository.updatePurchasedCouponByCustomer(customerId, coupon.getId());
	}

	/**
	 * method get customer coupons.
	 * 
	 * @param customerId
	 * @return all the customer's coupons by list from DB.
	 */
	public ArrayList<Coupon> getCustomerCoupons(int customerId) {
		return couponRepository.findByCustomerId(customerId);
	}

	/**
	 * method get coupons by category
	 * 
	 * @param customerId
	 * @param category
	 * @return the coupons of customer, filtered by category.
	 */
	public ArrayList<Coupon> getCustomerCouponsByCategory(int customerId, Category category) {
		return couponRepository.findByCategoryAndCustomerId(customerId, category.compareTo(category));

	}

	/**
	 * get coupon by max price
	 * 
	 * @param customerId - the customer id that made login
	 * @param maxPrice   - the maximum price to filter coupons
	 * @return all coupons equal & less the max price chosen.
	 */
	public ArrayList<Coupon> getCustomerCouponsByMaxPrice(int customerId, double maxPrice) {
		return customerRepository.findAllCouponsByCustomerIdAndMaxPrice(customerId, maxPrice);
	}

	/**
	 * get customer method
	 * 
	 * @param customerId - the id of customer that made login
	 * @return customer's detail by id.
	 */
	public Customer getCustomer(int customerId) {
		return customerRepository.findById(customerId);
	}
	/**
	 * get customer method by email and password method.
	 * 
	 * @param email
	 * @param password
	 * @return the customer.
	 */
	public Customer getCustomerByEmailAndPassword(String email, String password ) {
		return customerRepository.findByEmailAndPassword(email, password);
	}
}
