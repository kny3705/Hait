package com.trainspotting.hait.exception;

@SuppressWarnings("serial")
public class LoginFailedException extends RuntimeException {

	public LoginFailedException() {
		this(null);
	}
	
	public LoginFailedException(String message) {
		super(message);
	}
}
