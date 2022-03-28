package com.chana.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chana.beans.Category;
import com.chana.beans.Company;
import com.chana.beans.Coupon;
import com.chana.exceptions.LoginException;
import com.chana.login.LoginManager;
import com.chana.login.LoginRequest;
import com.chana.login.TokenManager;
import com.chana.service.GuestService;
import com.chana.utils.ClientType;

@RestController
@RequestMapping("/guest")
public class GuestController {
	@Autowired
	private GuestService guestService;
	
	
//	@Autowired
//	private LoginManager loginManager;
//	@Autowired
//	private TokenManager tokenManager;
	
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws LoginException {
//		loginManager.login(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getClientType());
//		String token = tokenManager.generageToken(loginRequest.getClientType()).toString();
//		
//		 return new ResponseEntity<>(token,HttpStatus.ACCEPTED);
//	}
	
	@GetMapping("/coupons")
	public ArrayList<Coupon> getAllCoupons() {
		return guestService.getAllCoupons();
	}
	
	@GetMapping("/coupons/category")
	public ArrayList<Coupon> getAllCouponsByCategory(@Valid @RequestParam Category category) {
		return guestService.getAllCouponsByCategory(category);
	}
	
	@GetMapping("/company")
	public ArrayList<Company> getAllCompanies() {
		return  guestService.getAllCompany();
	}
}
