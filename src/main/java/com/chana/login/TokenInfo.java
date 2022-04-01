package com.chana.login;

import java.util.Date;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
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
//@Component
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS , value = "prototype")
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
