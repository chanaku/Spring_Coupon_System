package com.chana.exceptions;

public class LoginException extends Exception {
	private String massage;

	public LoginException() {
		super("login error!");
	}

	public LoginException(String massage) {
		super(massage);
		this.massage = massage;
	}

}
