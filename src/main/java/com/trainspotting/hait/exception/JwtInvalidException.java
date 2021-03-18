package com.trainspotting.hait.exception;

@SuppressWarnings("serial")
public class JwtInvalidException extends RuntimeException {

	public JwtInvalidException() {
		super("INVALID_TOKEN");
	}
}
