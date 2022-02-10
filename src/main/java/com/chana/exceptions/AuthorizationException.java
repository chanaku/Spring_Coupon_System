package com.chana.exceptions;

public class AuthorizationException extends Exception{
	private String massage;
	
	public AuthorizationException() {
		super("faild on authorization");
	}

	public AuthorizationException(String massage) {
		super(massage);
		this.massage = massage;
	}

}
