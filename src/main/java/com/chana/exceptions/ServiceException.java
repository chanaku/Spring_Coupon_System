package com.chana.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class ServiceException extends Exception{
	private String massage;
	
	public ServiceException(String massage) {
		super(massage);
		this.massage=massage;
	}
}
