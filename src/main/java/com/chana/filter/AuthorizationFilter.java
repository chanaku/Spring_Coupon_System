package com.chana.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.chana.login.TokenManager;

@WebFilter("/customer/*")
public class AuthorizationFilter implements Filter{
	
	@Autowired
	private TokenManager tokenManager;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("this is filter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		boolean isAuthorized = tokenManager.isTokenExists(httpRequest.getHeader("authorization"));
		if (isAuthorized) {
			chain.doFilter(request, response);
			return;
		}
		httpResponse.sendError(HttpStatus.FORBIDDEN.value());
	} 
	
}
