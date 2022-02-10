package com.chana.beans;

import java.util.List;

public class CouponList {
	List<Coupon> coupons;

	public CouponList(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "CouponList [coupons=" + coupons + "]";
	};
	
	
}
