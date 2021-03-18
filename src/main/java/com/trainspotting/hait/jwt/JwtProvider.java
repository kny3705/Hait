package com.trainspotting.hait.jwt;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
	
	private final Key key;
	
	public JwtProvider(String secret) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public JwtToken provideToken(String id, String role) {
		return provideToken(generateToken(id, role));
	}

	public JwtToken provideToken(String token) {
		return new JwtToken(token, key);
	}
	
	private String generateToken(String id, String role) {
		Date expiry = new Date();
		expiry.setTime(expiry.getTime() + (1000 * 60 * 60 * 2));
//		expiry.setTime(expiry.getTime() + (500 * 60 * 1));
		
		String token = Jwts.builder()
							.setSubject(id)
							.setExpiration(expiry)
							.claim("role", role)
							.signWith(key, SignatureAlgorithm.HS256)
							.compact();
		return token;
	}
}
