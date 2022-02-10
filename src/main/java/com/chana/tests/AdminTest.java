package com.chana.tests;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import com.chana.beans.Company;
import com.chana.exceptions.AddCompanyException;
import com.chana.exceptions.UpdateCompanyException;
import com.chana.service.AdminService;

//@Component
@Order(1)
public class AdminTest implements CommandLineRunner {

	@Autowired
	private AdminService admin;
	private Company company = new Company("btl", "btl@btl.com", "123456");
	private Company company1 = new Company("btl", "btl@btl.com", "525252");
	/**
	 * run method - all the methods in, running when spring start this 'run' method. 
	 * for testing all the admin methods.
	 */
	@Override
	public void run(String... args) throws Exception {
		 adminLoginTest();
		 addCompany();
	}
	
	public void adminLoginTest() {
		System.out.println("admin login success:");
		try {
			admin.login("admin@admin.com", "admin");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("admin login faild:");
		try {
			admin.login("dsadasda@fgfdgdf", "sad");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void addCompany() throws AddCompanyException {
		System.out.println("add company success:");
		try {
			admin.addCompany(company);
		} catch (AddCompanyException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("add company faild:");
		try {
			admin.addCompany(company);
		} catch (AddCompanyException e) {
			System.out.println(e.getMessage());
		}
	}
	public void updateCompany() throws UpdateCompanyException {
		System.out.println("update company success:");
		try {
			admin.updateCompany(company1);
		} catch (UpdateCompanyException e) {
			System.out.println(e.getMessage());
		}
	}
	public void deleteCompany() {
		System.out.println("remove company success:");
		admin.deleteCompany(12);
	}
	public ArrayList<Company> getAllCompanies(){
		System.out.println("getting all companies success:");
		return admin.getAllCompany();
	}
	public Company getCompany(){
		System.out.println("getting one companies success:");
		return admin.getOneCompany(4);
	}
	
}
