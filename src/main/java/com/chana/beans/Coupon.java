package com.chana.beans;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author User
 * @since 1.0.0
 * 
 */
@Entity
@Table(name = "coupons")
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	@JsonBackReference
	@ManyToOne(cascade = {CascadeType.MERGE})
	private Company company;

	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "category_id")
	@NotNull(message="enter category is required")
	private Category category;
	@NotNull(message="enter title is required")
	private String title;
	@NotNull(message="enter description is required")
	private String description;
//	@NotNull(message="enter start date is required")
//	@PastOrPresent
	@Column(name = "start_date")
	private Date startDate;
//	@NotNull(message="enter end date is required")
//	@Future
	@Column(name = "end_date")
	private Date endDate;
	@NotNull(message="enter amount is required")
	@Min(value=1, message = "amount can't be less then 1.")
	private int amount;
//	@Min(value=1, message = "price can't be less then 1.")
//	@NotNull(message="enter price is required")
	private double price;
	private String image;

	/**
	 * an empty ctor.
	 */
	public Coupon() {
	}

	/**
	 * A full ctor with all the params:
	 * 
	 * @param id
	 * @param company
	 * @param category
	 * @param title
	 * @param description
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param price
	 * @param image
	 */
	public Coupon(int id, Company company, com.chana.beans.Category category, String title, String description,
			Date startDate, Date endDate, int amount, double price, String image) {
		this.id = id;
		this.company = company;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	/**
	 * A partial ctor:
	 * 
	 * @param company
	 * @param category
	 * @param title
	 * @param description
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param price
	 * @param image
	 */
	public Coupon(Company company, com.chana.beans.Category category, String title, String description, Date startDate,
			Date endDate, int amount, double price, String image) {
		this.company = company;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", companyId=" + company.getId() + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}

}
