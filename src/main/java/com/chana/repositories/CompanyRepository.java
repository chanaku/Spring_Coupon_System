package com.chana.repositories;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chana.beans.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	boolean existsByEmail(String email);

	ArrayList<Company> findAll();

	Company findById(int companyId);

	boolean existsByName(String name);

	boolean existsByIdAndName(int id, String name);

	boolean existsByEmailAndPassword(String email, String password);

	Company findByEmailAndPassword(String email, String password);

	/**
	 * method with custom query
	 */
	@Transactional
	@Modifying
	@Query(value = "delete from customers_vs_coupons where coupons_id in "
			+ "(select id from coupons where company_id = ?1)", nativeQuery = true)
	void deleteCouponsFromCustomersVsCouponsByCompanyId(int companyId);

}
