package com.chana.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chana.beans.Category;
import com.chana.beans.Coupon;
import com.chana.beans.CouponList;
import com.chana.beans.Customer;
import com.chana.exceptions.PurchaseCouponException;
import com.chana.exceptions.ServiceException;
import com.chana.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController extends ClientController{
	private CustomerService customerService;
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	
	
	@PutMapping
	public ResponseEntity<?> purchaseCoupon(@Valid @RequestBody Coupon coupon){
		try {
			customerService.purchaseCoupon(coupon);
		} catch (PurchaseCouponException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/coupon")
	public List<Coupon> getCustomerCoupons(){
		return customerService.getCustomerCoupons(customerService.getCustomerId());
	}
	
	@GetMapping("/coupon/category")
	public ArrayList<Coupon> getCouponsByCategory(@RequestParam Category category) {
		return customerService.getCustomerCouponsByCategory(customerService.getCustomerId(), category);
	}
	
	@GetMapping("/coupon/{maxPrice}")
	public ArrayList<Coupon> getCouponsByMaxPrice(@PathVariable("maxPrice") int maxPrice) {
		return customerService.getCustomerCouponsByMaxPrice(customerService.getCustomerId(), maxPrice);
	}
	
	@GetMapping
	public Customer getCustomer() {
		return customerService.getCustomer(customerService.getCustomerId());
	}
}
