package com.chana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.chana.service.AdminService;
import com.chana.service.CompanyService;
import com.chana.service.CustomerService;

@RestController
public abstract class ClientController {
	@Autowired
	private AdminService adminService;
	private CompanyService companyService;
	private CustomerService customerService;
	
	
	public boolean login() {
		return false;
	}
}
