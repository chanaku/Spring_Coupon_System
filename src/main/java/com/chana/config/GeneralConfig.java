package com.chana.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chana.login.TokenInfo;

@Configuration
public class GeneralConfig {
	
	@Bean
	public Map<String, TokenInfo> tokens(){
		return new HashMap<>();
	}

}
