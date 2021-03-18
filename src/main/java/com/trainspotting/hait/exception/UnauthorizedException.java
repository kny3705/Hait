package com.trainspotting.hait.exception;

@SuppressWarnings("serial")
public class UnauthorizedException extends RuntimeException {
	
	public UnauthorizedException() {
		super("UNAUTHORIZED");
	}
}

