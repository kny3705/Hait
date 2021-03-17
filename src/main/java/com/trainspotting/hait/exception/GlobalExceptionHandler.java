package com.trainspotting.hait.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.trainspotting.hait.ResponseBody;

/*
 * 400 - bad request
 * 401 - unauthenticated
 * 403 - forbidden (unauthorized)
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

	Logger log = LoggerFactory.getLogger("Global exception handler\n\t");

	// FIX LoginFailedException handler
	@ExceptionHandler(LoginFailedException.class)
	protected ResponseEntity<ResponseBody> handler(LoginFailedException e) {
		log.info("LoginFailedException handler - {}", e.getMessage());
		return null;
	}
	
	// FIX AccessDeniedException handler
	@ExceptionHandler(AccessDeniedException.class)
	protected ResponseEntity<ResponseBody> handler(AccessDeniedException e) {
		log.info("AccessDeniedException handler - {}", e.getMessage());
		return null;
	}
	
}
