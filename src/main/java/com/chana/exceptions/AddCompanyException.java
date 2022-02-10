package com.chana.exceptions;

public class AddCompanyException extends Exception{
	private String massage;

	
	public AddCompanyException() {
		super("add company error.");
	}


	public AddCompanyException(String massage) {
		super(massage);
		this.massage = massage;
	}
	
}
