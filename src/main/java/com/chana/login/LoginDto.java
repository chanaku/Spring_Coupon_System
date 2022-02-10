package com.chana.login;

import com.chana.service.ClientService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoginDto {
	private ClientService client;
	private TokenManager tokenManager;
	
}
