package com.chana.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/company")
public class CompanyController extends ClientController{
	private CompanyService companyService;
	private LoginManager loginManager;
	private TokenManager tokenManager;

	@Autowired

	public CompanyController(CompanyService companyService, LoginManager loginManager) {
		this.companyService = companyService;
		this.loginManager = loginManager;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws LoginException {
		loginManager.login(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getClientType());
		String token = tokenManager.generageToken(ClientType.COMPANY).toString();
		
		 return new ResponseEntity<>(token,HttpStatus.ACCEPTED);
	}
	// add coupon
	@PostMapping
	public ResponseEntity<?> addCoupon(@Valid @RequestBody Coupon coupon) {
		try {
			companyService.addCoupon(coupon, companyService.getCompanyId());
		} catch (AddCouponException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity(HttpStatus.CREATED);
	}

	// update coupon
	@PutMapping("/coupon")
	public ResponseEntity<?> updateCoupon(@Valid @RequestBody Coupon coupon) {
		companyService.updateCoupon(coupon);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

	// delete coupon by id
	@DeleteMapping("/coupon/{id}")
	public ResponseEntity<?> deleteCouponById(@Valid @PathVariable("id") int id) {
		companyService.deleteCoupon(id);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	@GetMapping("/coupon/category")
	public CouponList getAllCompanies(@Valid @RequestParam Category category) {
		return new CouponList(companyService.getCompanyCoupons(category, companyService.getCompanyId()));
	}
	
	@GetMapping("/coupon/{maxPrice}")
	public CouponList getAllCompanies(@Valid @PathVariable("maxPrice") int maxPrice) {
		return new CouponList(companyService.getCompanyCoupons(maxPrice, companyService.getCompanyId()));
	}
	
	@GetMapping
	public ResponseEntity<?> getCompany(){
		return new ResponseEntity<Company>(companyService.getCompanyDetails(companyService.getCompanyId()), HttpStatus.OK);
	}
	
}
