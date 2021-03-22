package com.trainspotting.hait.jwt;

import java.security.Key;

import com.trainspotting.hait.exception.JwtInvalidException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtToken {
	
	private final String token;
	private final Key key;

	public JwtToken(String token, Key key) {
		this.token = token;
		this.key = key;
	}
	
	public String getToken() {
		return token;
	}
	
	public Claims getData() {
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		} catch (Exception e) {
			throw new JwtInvalidException();
		}
	}
}
