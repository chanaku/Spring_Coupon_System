package com.chana.repositories;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chana.beans.Coupon;
import com.chana.beans.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	boolean existsByEmailAndPassword(String email, String password); 

	void deleteById(int customerId);

	ArrayList<Customer> findAll();

	Customer findById(int CustomerId);

	Customer findByEmailAndPassword(String email, String password);
	boolean existsByEmail(String email);

	/**
	 * Custom methods: each method do what her name means.
	 */
//	@Transactional
//	@Modifying
//	@Query(value = "select * from customers where email =?1", nativeQuery = true)
//	boolean isCustomerEmailExist(String email);

	@Transactional
	@Modifying
	@Query(value = "select cp.*, ct.name from coupons cp, categories ct,"
			+ " customers_vs_coupons vs, customers cs, companies cmp"
			+ " where ct.id=cp.CATEGORY_ID and cp.COMPANY_ID =cmp.id"
			+ " and cp.id=vs.COUPON_ID and vs.CUSTOMER_ID=cs.id and cs.id=? and cp.price<=?", nativeQuery = true)
	ArrayList<Coupon> findAllCouponsByCustomerIdAndMaxPrice(int customerId, double naxPrice);
	// check if the name of VS table will be like in the qeury.
	
	@Transactional
	@Modifying
	@Query(value = "select cp.*, ct.name from coupons cp, categories ct,"
			+ " customers_vs_coupons vs, customers cs, companies cmp"
			+ " where ct.id=cp.CATEGORY_ID and cp.COMPANY_ID =cmp.id"
			+ " and cp.id=vs.COUPON_ID and vs.CUSTOMER_ID=cs.id and cs.id=?", nativeQuery = true)
	ArrayList<Coupon> findAllCouponsByCustomerId(int customerId);

	@Query(value = "select * from customers_vs_coupons where customer_id =? and coupon_id = ?", nativeQuery = true)
	Optional<String> existsPurchesedCoupon(int customerId, int couponId);
}
