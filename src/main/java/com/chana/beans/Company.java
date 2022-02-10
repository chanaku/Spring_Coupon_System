package com.chana.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "companies")
public class Company {
	/**
	 * Entity's:
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "name is required")
	@Column
	@Size(min = 3) //add " " required
	private String name;
	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;
	@NotNull(message = "password is required")
	@Size(min = 6, message = "password must have at least 6 characters")
	private String password;
	@JsonManagedReference
	@OneToMany(mappedBy = "company")
	private List<Coupon> coupons;

	/**
	 * An empty ctor
	 */
	public Company() {
	}

	/**
	 * A full ctor with all the params:
	 * 
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param coupons
	 */
	public Company(int id, String name, String email, String password, List<Coupon> coupons) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}

	/**
	 * A partial ctor with some params:
	 */
	public Company(String name, String email, String password, List<Coupon> coupons) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}

	public Company(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * getter's and setter's:
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

}
