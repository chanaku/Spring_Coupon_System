package com.chana.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.chana.login.TokenInfo;
import com.chana.login.TokenInfo.TokenInfoBuilder;
import com.chana.utils.ClientType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "companies")
public class CompanyUpdate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "name is required")
//	@Column
//	@Size(min = 3) //add " " required
	private String name;
//	@NotBlank(message = "Email is required")
//	@Email(message = "Email should be valid")
	private String email;
//	@NotNull(message = "password is required")
//	@Size(min = 6, message = "password must have at least 6 characters")
	private String password;
}
