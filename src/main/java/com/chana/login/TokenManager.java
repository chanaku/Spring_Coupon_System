package com.chana.login;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chana.utils.ClientType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenManager {
	@Autowired
	private Map<String, TokenInfo> tokens;
	
	public boolean isTokenExists(String token) {
		return tokens.get(token) != null;
	}
	
	public String generageToken(ClientType type) {
		TokenInfo info = TokenInfo.generate(type);
		tokens.put(info.getToken(), info);
		return info.getToken();
										
	}
	public void removeToken(String token) {
		tokens.remove(token);
	}
	private boolean isTokenExpired(Date time) {
		return new Date().after(DateUtils.addMinutes(time, 30));
	}
	public void removeExpired() {
		tokens.entrySet().removeIf((entry)-> 
				isTokenExpired(entry.getValue().getCreationDate()));
	}
}
