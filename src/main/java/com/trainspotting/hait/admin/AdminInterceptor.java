package com.trainspotting.hait.admin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.trainspotting.hait.exception.UnauthenticatedException;
import com.trainspotting.hait.exception.UnauthorizedException;
import com.trainspotting.hait.jwt.JwtProvider;

import io.jsonwebtoken.Claims;

public class AdminInterceptor extends HandlerInterceptorAdapter {
	private final JwtProvider jwtProvider;
	
	public AdminInterceptor(JwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Cookie[] cookies = request.getCookies();
		if(cookies == null) throw new UnauthenticatedException();
		
		String token = getToken(cookies);
		if(token == null) throw new UnauthenticatedException();
		
		Claims claims = jwtProvider.provideToken(token).getData();
		if(!"ADMIN".equals(claims.get("role"))) throw new UnauthorizedException();
		
		return true;
	}
	
	private String getToken(Cookie[] cookies) {
		String token = null;
		for(Cookie cookie : cookies) {
			if("admin_token".equals(cookie.getName())) {
				token = cookie.getValue();
			}
		}
		return token;
	}
}
