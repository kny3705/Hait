package com.trainspotting.hait.exception;

@SuppressWarnings("serial")
public class UnauthenticatedException extends RuntimeException {
	
	public UnauthenticatedException() {
		super("UNAUTHENTICATED");
	}
}
