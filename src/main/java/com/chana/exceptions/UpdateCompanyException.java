package com.chana.exceptions;

public class UpdateCompanyException extends Exception{
	private String massage;

	public UpdateCompanyException() {
		super("error while update company");
	}

	public UpdateCompanyException(String massage) {
		super(massage);
		this.massage = massage;
	}
	
}
