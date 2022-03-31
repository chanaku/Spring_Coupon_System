package com.chana.login;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.chana.utils.ClientType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Setter
@Component
public class TokenInfo {
	private String token;
	private Date creationDate;
	private ClientType clientType;
	private int userId;
	
	public static TokenInfo generate(ClientType type) {
		return TokenInfo.builder()
				.token(UUID.randomUUID().toString())
				.creationDate(new Date())
				.clientType(type)
				.build();
								
	}

	public void setUserId(int id) {
		this.userId=id;
		
	}
}
