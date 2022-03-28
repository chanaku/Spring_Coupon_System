package com.chana.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chana.beans.Category;
import com.chana.beans.Coupon;
import com.chana.beans.CouponList;
import com.chana.beans.Customer;
import com.chana.exceptions.LoginException;
import com.chana.exceptions.PurchaseCouponException;
import com.chana.exceptions.ServiceException;
import com.chana.login.LoginManager;
import com.chana.login.LoginRequest;
import com.chana.login.TokenManager;
import com.chana.service.CustomerService;
import com.chana.utils.ClientType;

@RestController
@RequestMapping("/customer")
public class CustomerController extends ClientController{
	private CustomerService customerService;
	@Autowired
	private LoginManager loginManager;
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws LoginException {
		loginManager.login(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getClientType());
		String token = tokenManager.generageToken(ClientType.CUSTOMER).toString();
		
		 return new ResponseEntity<>(token,HttpStatus.ACCEPTED);
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
	
	@GetMapping("/coupons")
	public List<Coupon> getCustomerCoupons(){
		return customerService.getCustomerCoupons(customerService.getCustomerId());
	}
	
	@GetMapping("/coupons/{category}")
	public ArrayList<Coupon> getCouponsByCategory(@PathVariable("category") Category category) {
		return customerService.getCustomerCouponsByCategory(customerService.getCustomerId(), category);
	}
	
	@GetMapping("/coupons/{maxPrice}")
	public ArrayList<Coupon> getCouponsByMaxPrice(@PathVariable("maxPrice") int maxPrice) {
		return customerService.getCustomerCouponsByMaxPrice(customerService.getCustomerId(), maxPrice);
	}
	
	@GetMapping
	public Customer getCustomer() {
		return customerService.getCustomer(customerService.getCustomerId());
	}
}
