package com.chana.repositories;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chana.beans.Category;

import com.chana.beans.Coupon;
import com.chana.exceptions.ServiceException;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {



	void deleteById(int couponId);

	ArrayList<Coupon> findAll();

	Coupon findById(int couponId);

	List<Coupon> findByCategoryAndCompanyId(Category category, int companyId);

	ArrayList<Coupon> findByPriceLessThanEqual(double maxPrice);

	ArrayList<Coupon> findByCompanyId(int companyId);
	
	ArrayList<Coupon> findByCategory(Category category);

	ArrayList<Coupon> findByPriceLessThanEqualAndCompanyId(double maxPrice, int companyId);

	ArrayList<Coupon> findBycompanyIdAndPriceLessThanEqual(int customerId, double maxPrice);
	
	boolean existsByIdAndEndDateBefore(int id, Date date);
	
	boolean existsByIdAndAmountEquals(int id, int zero);
	boolean existsByTitleAndCompanyId(String title, int companyId);

	/**
	 * Custom methods: each method do what her name means.
	 */

	@Transactional
	@Modifying
	@Query(value = "delete from coupons where end_date<now()", nativeQuery = true)
	void deleteExpiredCoupons();

//	@Query(value = "select * from coupons where company_id =?1 and title=?1", nativeQuery = true)
//	boolean isCompanyCouponTitleExist(int companyId, String title);

//	@Query(value = "select * from coupons where id =?1 and end_date<now()", nativeQuery = true)
//	Boolean existsByIdAndEndDateBefore(Integer companyId);

	@Transactional
	@Modifying
	@Query(value = "update coupons set amount= amount-1 where id=?", nativeQuery = true)
	void updateCouponQuantity(int couponId);

	@Transactional
	@Modifying
	@Query(value = "insert into customers_vs_coupons(CUSTOMER_ID,COUPON_ID)values(?,?)", nativeQuery = true)
	void updatePurchasedCouponByCustomer(int customerId, int couponId);

	@Transactional
	@Modifying
	@Query(value = "update coupons set COMPANY_ID =?,CATEGORY_ID=?,TITLE=?,DESCRIPTION=?,START_DATE=?,END_DATE=?,AMOUNT=?,PRICE=?,IMAGE=? where id=?", nativeQuery = true)
	void updateCoupon(int companyId, int categoryId, String title, String desc, Date sDate, Date d_date, int amount,
			double price, String image, int couponId);

	@Query(value = "select * from coupons left join customers_vs_coupons on coupons.id= customers_vs_coupons.coupon_id where"
			+ " customers_vs_coupons.customer_id=? and coupons.category_id=?", nativeQuery = true)
	ArrayList<Coupon> findByCategoryAndCustomerId(int customerId, int categoryId);

	@Query(value = "select * from coupons left join customers_vs_coupons on coupons.id= customers_vs_coupons.coupon_id where"
			+ " customers_vs_coupons.customer_id=?", nativeQuery = true)
	ArrayList<Coupon> findByCustomerId(int customerId);

//	boolean isCouponExpierd(int id, ZonedDateTime zonedDateTime);
}
