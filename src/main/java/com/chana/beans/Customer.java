package com.chana.beans;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "first name is required.")
	@Size(min=2, message ="name can't be less then 2 characters")
	@Size(max=20, message ="name can't be longer then 20 characters")
	@Column(name = "first_name")
	private String firstName;
	@NotBlank(message = "last name is required.")
	@Size(min=2, message ="last name can't be less then 2 characters")
	@Size(max=20, message ="last name can't be longer then 20 characters")
	@Column(name = "last_name")
	private String lastName;
	@Email(message = "please enter valid email.")
	private String email;
	@NotNull(message ="password can't be null.")
	@Size(min = 6, message = "password must have at least 6 characters")
	private String password;
	@ManyToOne(cascade = CascadeType.ALL) // or ManyToMany?
	@JoinTable(
			name = "customers_vs_coupons", 
			joinColumns = @JoinColumn(name = "customer_id"), 
			inverseJoinColumns = @JoinColumn(name = "coupon_id"))
	private Coupon coupon;

	/**
	 * An empty ctor:
	 */
	public Customer() {
	}

	/**
	 * A full ctor with all the params:
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param coupons
	 */
	public Customer(int id, String firstName, String lastName, String email, String password,
			ArrayList<Coupon> coupons) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.coupon = coupon;
	}

	/**
	 * A partial ctors:
	 */
	public Customer(String firstName, String lastName, String email, String password, ArrayList<Coupon> coupons) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.coupon = coupon;
	}

	public Customer(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	/**
	 * getter's and setter's:
	 * 
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Coupon getCoupons() {
		return coupon;
	}

	public void setCoupons(Coupon coupon) {
		this.coupon = coupon;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", coupons=" + coupon + "]";
	}

}
