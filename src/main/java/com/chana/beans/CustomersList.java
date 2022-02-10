package com.chana.beans;

import java.util.ArrayList;
import java.util.List;

public class CustomersList {
	List<Customer> customers;

	public CustomersList(List<Customer> customers) {
		super();
		this.customers = customers;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "CustomersList [customers=" + customers + "]";
	}
	
	
	
}
