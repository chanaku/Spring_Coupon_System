package com.chana.tests;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.chana.beans.Category;
import com.chana.beans.Company;
import com.chana.beans.Coupon;
import com.chana.exceptions.AddCouponException;
import com.chana.exceptions.ServiceException;
import com.chana.service.CompanyService;

//@Component
@Order(2)
public class CompanyTest implements CommandLineRunner {

	@Autowired
	private CompanyService companyService;
	int companyId;

	Company company1;
	Coupon coupon1;
	Coupon coupon2;
	Coupon coupon3;

	/**
	 * run method - all the methods in, running when spring start this 'run' method.
	 * for testing all the company methods.
	 */
	@Override
	public void run(String... args) throws Exception {
		companyLoginTest();
		addDummyCompaneisToDatabase();
		addCoupon();
		updateCoupon();
		deleteCoupon();
		getCompanyCoupons();
		getCompanyCouponsByCategory();
		getCompanyCouponsByMaxPrice();
		getCompanyDetails();

	}

	public void addDummyCompaneisToDatabase() {
		company1 = companyService.getCompanyDetails(companyId);
		coupon1 = new Coupon(65, company1, Category.FOOD, "happy hanukka", "buy 2 products and pay only for 1",
				Date.valueOf("2021-10-13"), Date.valueOf("2022-01-31"), 250, 10.0, "htyr");
		coupon2 = companyService.getCouponById(46);
		coupon3 = companyService.getCouponById(48);
	}

	public void companyLoginTest() {
		System.out.println("company login success:");
		try {
			companyService.login("gold@gold-line.com", "123456");
			companyId = companyService.getCompanyDetailsByEmailAndPassword("gold@gold-line.com", "123456").getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("company login faild:");
		try {
			companyService.login("dsadasda@fgfdgdf", "sad");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("login with admin details:");
		try {
			companyService.login("admin@admin.com", "admin");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("login with customer details:");
		try {
			companyService.login("shir@gmail.com", "123456");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void addCoupon() throws AddCouponException {
		try {
			companyService.addCoupon(coupon1, companyService.getCompanyId());
		} catch (AddCouponException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("success adding coupon.");
	}

	public void updateCoupon() {
		System.out.println("success updating coupon.");
		companyService.updateCoupon(coupon2);
	}

	public void deleteCoupon() {
		companyService.deleteCoupon(coupon3.getId());
		System.out.println("success delete coupon.");
	}

	public ArrayList<Coupon> getCompanyCoupons() {
		System.out.println("success get all company coupons.");
		return companyService.getCompanyCoupons(companyId);
	}

	public List<Coupon> getCompanyCouponsByCategory() {
		System.out.println("success get all company coupons by category.");
		return companyService.getCompanyCoupons(Category.VACATION, companyId);
	}

	public List<Coupon> getCompanyCouponsByMaxPrice() {
		System.out.println("success get all company coupons by max price.");
		return companyService.getCompanyCoupons(51.0, companyId);
	}

	public Company getCompanyDetails() {
		System.out.println("success get all company details.");
		return companyService.getCompanyDetails(companyId);
	}
}
