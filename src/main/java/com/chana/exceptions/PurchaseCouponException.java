package com.chana.exceptions;

public class PurchaseCouponException extends Exception{
	private String massage;
	
	public PurchaseCouponException() {
		super("error while trying purchase coupon");
	}
	public PurchaseCouponException(String massage) {
		super(massage);
		this.massage=massage;
	}
}
