package com.chana.tests;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.chana.beans.Category;
import com.chana.beans.Company;
import com.chana.beans.Coupon;
import com.chana.beans.Customer;
import com.chana.exceptions.ServiceException;
import com.chana.repositories.CustomerRepository;
import com.chana.service.AdminService;
import com.chana.service.CustomerService;

//@Component
@Order(3)
public class CustomerTest implements CommandLineRunner {
	@Autowired
	private CustomerService customer;
	int customerId = 0;
	Coupon coupon;
	Company company1;

	/**
	 * run method - all the methods in, running when spring start this 'run' method.
	 * for testing all the customer methods.
	 */
	@Override
	public void run(String... args) throws Exception {
		customerLoginTest();
		purchaseCoupon();
		getCustomerCoupons();
		getCustomerCouponsByCategory();
		getCustomerCouponsByMaxPrice();
		getCustomerCouponsByMaxPrice();
		getCustomerDetails();
	}

	public void addDummyCustomerToDatabase() {
		company1 = new Company("looha", "loo@haa.com", "123456");
		coupon = new Coupon(company1, Category.FOOD, "take 2 pay for 1", "buy 2 product and pay only for 1",
				Date.valueOf("2021-10-13"), Date.valueOf("2021-12-31"), 250, 10.0, "htyr");
	}

	public void customerLoginTest() {
		System.out.println("customer login success:");
		try {
			customer.login("shir@gmail.com", "123456");
			customerId = customer.getCustomerByEmailAndPassword("shir@gmail.com", "123456").getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("customer login faild:");
		try {
			customer.login("vfdfd@bfds.fd", "sad");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("login with admin details:");
		try {
			customer.login("admin@admin.com", "admin");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("login with company details:");
		try {
			customer.login("shir@gmail.com", "123456");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void purchaseCoupon() {
		System.out.println("purchase coupon:");
		try {
			customer.purchaseCoupon(coupon);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Coupon> getCustomerCoupons() {
		System.out.println("get all coupons");
		return customer.getCustomerCoupons(customerId);
	}

	public List<Coupon> getCustomerCouponsByCategory() {
		System.out.println("get all coupons by category");
		return customer.getCustomerCouponsByCategory(customerId, Category.ELECTRICITY);
	}

	public List<Coupon> getCustomerCouponsByMaxPrice() {
		System.out.println("get all coupons by max price");
		return customer.getCustomerCouponsByMaxPrice(customerId, 85.5);
	}

	public Customer getCustomerDetails() {
		System.out.println("get athe customer details");
		return customer.getCustomer(customerId);
	}

}
