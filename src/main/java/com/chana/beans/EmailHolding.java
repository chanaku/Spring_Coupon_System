package com.chana.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class EmailHolding {
	private String email;
	private String password;
	private String clientType;
	@Override
	public String toString() {
		return "EmailHolding [email=" + email + ", password=" + password + "]";
	}
	
	

	
	
}
