package com.chana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.chana.CTLTester.ControllerTestAdmin;
import com.chana.exceptions.ServiceException;
import com.chana.service.CompanyService;

/**
 * Coupons system !!
 * sell / buy coupons.
 * 
 * @author Chana Kurtz
 * @version JAVA 15.0.0
 * @version	 Spring-boot 2.5.5
 * @version maven 4.0.0
 * @version MySql 8.0.25
 * @since 11.2021
 */
@ServletComponentScan
@EnableScheduling
@SpringBootApplication
public class Main {

	/**
	 * coupon system - || start ! ! ||
	 * 
	 * @param ApplicationContext - the context pool to manage the Beans.
	 * @param AdminService       - actions as admin
	 * @param CompanyService     - actions as company
	 * @param CustomerService    - actions as customer
	 * @throws exception if there is any problem.
	 * 
	 */
	public static void main(String[] args) throws ServiceException {
		ApplicationContext context = SpringApplication.run(Main.class);
		CompanyService com = context.getBean(CompanyService.class);
		com.chana.CTLTester.ControllerTestAdmin tester = context.getBean(com.chana.CTLTester.ControllerTestAdmin.class);
		tester.testAdminApi();
	}
}
