package com.chana.beans;

import java.util.List;

public class CompanyList {
	List<Company>	companies;

	public CompanyList(List<Company> companies) {
		super();
		this.companies = companies;
	}
	
	public CompanyList() {}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	@Override
	public String toString() {
		return "[" + companies + "]";
	}
	
	
}
