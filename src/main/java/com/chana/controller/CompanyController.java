package com.chana.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chana.beans.Category;
import com.chana.beans.Company;
import com.chana.beans.CompanyList;
import com.chana.beans.Coupon;
import com.chana.beans.CouponList;
import com.chana.beans.EmailHolding;
import com.chana.exceptions.AddCouponException;
import com.chana.exceptions.LoginException;
import com.chana.exceptions.ServiceException;
import com.chana.login.LoginDto;
import com.chana.login.LoginManager;
import com.chana.login.LoginRequest;
import com.chana.login.TokenManager;
import com.chana.service.AdminService;
import com.chana.service.CompanyService;
import com.chana.utils.ClientType;

@CrossOrigin
@RestController
@RequestMapping("/company")
public class CompanyController extends ClientController{
	private CompanyService companyService;
	private LoginManager loginManager;
	private TokenManager tokenManager;

	@Autowired

	public CompanyController(CompanyService companyService, LoginManager loginManager,TokenManager tokenManager) {
		this.companyService = companyService;
		this.loginManager = loginManager;
		this.tokenManager = tokenManager;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws LoginException {
		System.out.println("this is login request "+ loginRequest);
		loginManager.login(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getClientType());
		String token = tokenManager.generageToken(ClientType.COMPANY).toString();
		
		 return new ResponseEntity<>(token,HttpStatus.ACCEPTED);
	}
	// add coupon
	@PostMapping("/coupons")
	public ResponseEntity<?> addCoupon(@Valid @RequestBody Coupon coupon) {
		System.out.println("this is coupon company: "+coupon.getCompany());
		try {
			companyService.addCoupon(coupon, companyService.getCompanyId());
		} catch (AddCouponException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity(HttpStatus.CREATED);
	}

	// update coupon
	@PutMapping("/coupons")
	public ResponseEntity<?> updateCoupon(@Valid @RequestBody Coupon coupon) {
		companyService.updateCoupon(coupon);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/mycoupons")
	public List<Coupon> getCompanyCoupons(){
		return companyService.getCompanyCoupons(companyService.getCompanyId());
	}
	
	@GetMapping("/coupons")
	public List<Coupon> getAllCoupons(){
		return companyService.getAllCoupons();
	}

	// delete coupon by id
	@DeleteMapping("/coupons/{id}")
	public ResponseEntity<?> deleteCouponById(@Valid @PathVariable("id") int id) {
		companyService.deleteCoupon(id);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	@GetMapping("/coupons/category/{category)")
	public List<Coupon> getAllCompanies(@Valid @PathVariable("category") Category category) {
		return companyService.getCompanyCoupons(category, companyService.getCompanyId());
	}
	
	@GetMapping("/coupons/{maxPrice}")
	public ArrayList<Coupon> getAllCompanies(@Valid @PathVariable("maxPrice") int maxPrice) {
		return companyService.getCompanyCoupons(maxPrice, companyService.getCompanyId());
	}
	
	@GetMapping
	public ResponseEntity<?> getCompany(){
		return new ResponseEntity<Company>(companyService.getCompanyDetails(companyService.getCompanyId()), HttpStatus.OK);
	}
	
	@PostMapping("/company")
	public ResponseEntity<?> getCompanyDetailsByEmilAndPassword(@Valid @RequestBody EmailHolding e){
		System.out.println("this is email: "+e.getEmail()+" and password: "+e.getPassword());
		return new ResponseEntity<Integer>(companyService.getCompanyDetailsByEmailAndPassword(e.getEmail(), e.getPassword()).getId(), HttpStatus.OK);
	}
}
