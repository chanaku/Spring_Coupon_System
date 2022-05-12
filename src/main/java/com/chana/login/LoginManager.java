package com.chana.login;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.chana.exceptions.LoginException;
import com.chana.service.AdminService;
import com.chana.service.ClientService;
import com.chana.service.CompanyService;
import com.chana.service.CustomerService;
import com.chana.utils.ClientType;

@Component // to be singleton //can put @Service anotation
public class LoginManager {
	private final ApplicationContext context;
	private final AdminService adminService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private TokenManager tokenManager;
	private ClientService clientService;

	@Autowired

	public LoginManager(ApplicationContext context, AdminService adminService, TokenManager tokenManager) {
		this.context = context;
		this.adminService = adminService;
		this.tokenManager = tokenManager;
	}


	/**
	 * this is login method. the method get the params email and password, and
	 * return the kind of the client type.
	 * 
	 * @param email: the client's email.
	 * @param password: the client's password.
	 * @return clientType- via the above params.
	 * @throws LoginException 
	 */
	public ClientService login(String email, String password, ClientType clientType) throws LoginException{
		
		switch(clientType) {
		case ADMINISTRATOR:
			clientService=(ClientService)adminService;
			
			break;
		case COMPANY:
			clientService=(ClientService)context.getBean(CompanyService.class);
//			tokenInfo.setUserId(companyService.getCompanyDetailsByEmailAndPassword(email, password).getId()); 
			break;
		case CUSTOMER:
			clientService=(ClientService)context.getBean(CustomerService.class);
//			tokenInfo.setUserId(customerService.getCustomerByEmailAndPassword(email, password).getId());
			break;
		}
		if (!clientService.login(email, password)) {
			throw new LoginException(clientType.name().toString() + " Unauthorized");
	}

		return clientService;
	}
	
	public void logout(String token) {
		tokenManager.removeToken(token);
	}
}
