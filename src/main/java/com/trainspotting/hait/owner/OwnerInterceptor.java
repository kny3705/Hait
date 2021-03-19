package com.trainspotting.hait.owner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.trainspotting.hait.jwt.JwtProvider;

public class OwnerInterceptor extends HandlerInterceptorAdapter {

	private final JwtProvider jwtProvider;
	
	public OwnerInterceptor(JwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		return false;
	}
}
