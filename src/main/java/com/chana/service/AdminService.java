package com.chana.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.chana.beans.Company;
import com.chana.beans.Coupon;
import com.chana.beans.Customer;
import com.chana.exceptions.AddCompanyException;
import com.chana.exceptions.AddCustomerException;
import com.chana.exceptions.LoginException;
import com.chana.exceptions.ServiceException;
import com.chana.exceptions.UpdateCompanyException;
import com.chana.repositories.CompanyRepository;
import com.chana.repositories.CouponRepository;
import com.chana.repositories.CustomerRepository;

@Service
public class AdminService extends ClientService {
	
	public AdminService(CompanyRepository companyRepository, CustomerRepository customerRepository,
			CouponRepository couponRepository) {
		super(companyRepository, customerRepository, couponRepository);
	}


	/**
	 * login method - for admin user.
	 * 
	 * @param email    - must be "admin@admin.com"
	 * @param password - must be "admin"
	 * @throws ServiceException while the details are incorrect
	 * @throws LoginException 
	 * 
	 */
	@Override
	public boolean login(String email, String password) throws LoginException {
		if (!email.equals("admin@admin.com")) {
			throw new LoginException("error while trying to login. user or password are incorrect");
		}
		if (!password.equals("admin")) {
			throw new LoginException("error while trying to login. user or password are incorrect");
		}
		return (email.equals("admin@admin.com") && password.equals("admin"));
	}

	/**
	 * add company method
	 * 
	 * @param company - the new company
	 * @throws ServiceException if company is exiting.
	 * @throws AddCompanyException 
	 */
	public void addCompany(Company company) throws  AddCompanyException {
		if (companyRepository.existsByEmail(company.getEmail())) {
			throw new AddCompanyException(
					"error in addCompany, where company name is:" + company.getName() + "email exist");
		}
		if (companyRepository.existsByName(company.getName())) {
			throw new AddCompanyException(
					"error in addCompany, where company name is:" + company.getName() + "name exist");
		} else {
			companyRepository.save(company);
		}
	}

	/**
	 * update company method
	 * 
	 * @param company is the company for update.
	 * @throws ServiceException if the user try update the id and name same as
	 *                          existing company.
	 * @throws UpdateCompanyException 
	 *  @see can't to ensure that the user didn't update the company code or name                     
	 */
	
	public void updateCompany(Company company) throws UpdateCompanyException {
		if (companyRepository.existsByIdAndName(company.getId(), company.getName())) {
			companyRepository.save(company);// change to save method
		} else {
			throw new UpdateCompanyException("can't update, the company is invalid");
		}
	}

	/**
	 * delete Company method
	 * 
	 * @param companyId to delete from DB the company with this ID.
	 * @see that this tables have a cascade setting, to delete with the company also the coupons 
	 *  and the coupon's purchases.
	 */
	
	public void deleteCompany(int companyId) {
		companyRepository.deleteById(companyId);
	}

	/**
	 * get all companies method
	 * @return all the companies from DB.
	 */
	public ArrayList<Company> getAllCompany() {
		return companyRepository.findAll();
	}

	/**
	 * get One Company method
	 * 
	 * @param companyId - to select the company from DB by this param.
	 * @return the company.
	 */
	public Company getOneCompany(int companyId) {
		return companyRepository.findById(companyId);
	}

	/**
	 * add Customer method
	 * 
	 * @param customer is the customer to add to DB.
	 * @throws AddCustomerException 
	 * @throws exception if the customer's email already exist. 
	 */
	public void addCustomer(Customer customer) throws AddCustomerException {
		if (customerRepository.existsByEmail(customer.getEmail())) {
			throw new AddCustomerException("the email of the customer already exist. can't add the customer "
					+ customer.getFirstName() + " " + customer.getLastName());
		} else {
			customerRepository.save(customer);
		}
	}

	/**
	 * update customer method
	 * 
	 * @param customer id the customer to update.
	 * note: can't ensure that the user didn't update the customer code
	 */

	public void updateCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	/**
	 * delete customer method
	 * 
	 * @param customerId is the id of the customer to delete.
	 * note: cascade.
	 */
	// add cascade to delete the coupons purchases history
	public void deleteCustomer(int customerId) {
		customerRepository.deleteById(customerId);
	}

	/**
	 * get all customers method
	 * 
	 * @return the all customers existing in DB.
	 */
	public ArrayList<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	/**
	 * get castomer method
	 * 
	 * @param CustomerId to get the customer by the id
	 * @return the customer from DB.
	 */
	public Customer getOneCustomer(int CustomerId) {
		return customerRepository.findById(CustomerId);
	}
	/**
	 * get all coupons method
	 * @return all coupons from system
	 */
	public ArrayList<Coupon> getAllCoupons(){
		return couponRepository.findAll();
	}
}
