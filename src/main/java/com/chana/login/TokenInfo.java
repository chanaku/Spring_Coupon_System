package com.chana.login;

import java.util.Date;
import java.util.UUID;

import com.chana.utils.ClientType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TokenInfo {
	private String token;
	private Date creationDate;
	private ClientType clientType;
	
	public static TokenInfo generate(ClientType type) {
		return TokenInfo.builder()
				.token(UUID.randomUUID().toString())
				.creationDate(new Date())
				.clientType(type)
				.build();
								
	}
}
