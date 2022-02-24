package com.chana.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chana.beans.Company;
import com.chana.beans.CompanyList;
import com.chana.beans.Coupon;
import com.chana.beans.CouponList;
import com.chana.beans.Customer;
import com.chana.beans.CustomersList;
import com.chana.exceptions.AddCompanyException;
import com.chana.exceptions.AddCustomerException;
import com.chana.exceptions.LoginException;
import com.chana.exceptions.ServiceException;
import com.chana.exceptions.UpdateCompanyException;
import com.chana.login.LoginDto;
import com.chana.login.LoginManager;
import com.chana.login.LoginRequest;
import com.chana.login.TokenManager;
import com.chana.service.AdminService;
import com.chana.utils.ClientType;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController extends ClientController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private LoginManager loginManager;
	@Autowired
	private TokenManager tokenManager;
	

	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	// try return response entity or loginDto with login and token
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) throws LoginException {
		loginManager.login(loginRequest.getEmail(), loginRequest.getPassword(), ClientType.ADMINISTRATOR);
		String token = tokenManager.generageToken(ClientType.ADMINISTRATOR).toString();
		
		 return new ResponseEntity<>(token,HttpStatus.ACCEPTED);
	}

	@GetMapping("/logout")
	public void logout(@RequestHeader("authorization") String token) {
		loginManager.logout(token);
	}

	// add company
	@PostMapping(path = "/company", 
			// method= RequestMethod.POST,
//		    produces = MediaType.APPLICATION_JSON_VALUE,
		    consumes = "application/json")
	public ResponseEntity<?> addCompany(@Valid @RequestBody Company company) {
		try {
			adminService.addCompany(company);
		} catch (AddCompanyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity(HttpStatus.CREATED);
	}


	// get company by path param
	@GetMapping("/company/{id}")
	public ResponseEntity<?> getCompanyById(@PathVariable("id") int id) {
		return new ResponseEntity<Company>(adminService.getOneCompany(id), HttpStatus.ACCEPTED);
	}

	// update company
	@PutMapping("/company")
	public ResponseEntity<?> updateCompany(@Valid @RequestBody Company company) {
		try {
			adminService.updateCompany(company);
		} catch (UpdateCompanyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // make the update
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

	// delete company by id
	@DeleteMapping("/company/{id}")
	public ResponseEntity<?> deleteCompanyById(@PathVariable("id") int id) {
		adminService.deleteCompany(id);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

	@GetMapping("/company")
	public ArrayList<Company> getAllCompanies() {
		return  adminService.getAllCompany();
	}

	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer) {
		try {
			adminService.addCustomer(customer);
		} catch (AddCustomerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@PutMapping("/customer")
	public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer customer) {
		adminService.updateCustomer(customer);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomerById(@Valid @PathVariable("id") int id) {
		adminService.deleteCustomer(id);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

	@GetMapping("/customer")
	public ArrayList<Customer> getAllCustomers() {
		return adminService.getAllCustomers();
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id") int id) {
		return new ResponseEntity<Customer>(adminService.getOneCustomer(id), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/coupons")
	public ArrayList<Coupon> getAllCoupons() {
		return adminService.getAllCoupons();
	}

}
