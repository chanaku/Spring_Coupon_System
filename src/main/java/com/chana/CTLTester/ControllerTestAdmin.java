package com.chana.CTLTester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.chana.beans.Company;
import com.chana.beans.Customer;

@Component
public class ControllerTestAdmin {

	@Autowired
	private RestTemplate restTemplate;
	private static final String SEVERAL_URL = "http://localhost:8080/admin";

	public void testAdminApi() {

		// login

		// add company
//		System.out.println("checking method addCompany");
//		System.out.println(String.format("%s", SEVERAL_URL));
//		Company newCompany = new Company("ford", "ford@ford.com", "123456");
//		ResponseEntity<Integer> responseNewCompany = restTemplate.postForEntity(String.format("%s", SEVERAL_URL),
//				newCompany, Integer.class);
//		System.out.println("response status: " + responseNewCompany.getStatusCodeValue());
//		System.out.println("response body: " + responseNewCompany.getBody());
		
		
		
		
		
		// add customer
//		System.out.println("checking method addCustomer");
//		System.out.println(String.format("%s/customer", SEVERAL_URL));
//		Customer newCustomer = new Customer("Shany", "livman","shanliv@gmail.com", "123456");
//		ResponseEntity<Integer> responseNewCustomer = restTemplate.postForEntity(String.format("%s/customer", SEVERAL_URL),
//				newCustomer, Integer.class);
//		System.out.println("response status: " + responseNewCustomer.getStatusCodeValue());
//		System.out.println("response body: " + responseNewCustomer.getBody());
		
	}

	
	
	
	
	
}
