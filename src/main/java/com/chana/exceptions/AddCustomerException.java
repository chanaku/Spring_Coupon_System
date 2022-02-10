package com.chana.exceptions;

public class AddCustomerException extends Exception {
	private String massage;

	public AddCustomerException() {
		super("error while adding customer");
	}

	public AddCustomerException(String massage) {
		super();
		this.massage = massage;
	}

}
