package com.chana.exceptions;

public class AddCouponException extends Exception{
	private String massage;
	
	public AddCouponException() {
		super("error while add coupon");
	}

	public AddCouponException(String massage) {
		super(massage);
		this.massage = massage;
	}
	
}
