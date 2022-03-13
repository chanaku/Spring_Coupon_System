package com.chana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chana.beans.CompanyUpdate;

public interface CompanyUpdateRepository extends JpaRepository<CompanyUpdate, Integer>{
	boolean existsById(int id);
}
