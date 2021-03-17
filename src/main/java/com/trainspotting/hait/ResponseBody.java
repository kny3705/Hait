package com.trainspotting.hait;

public class ResponseBody {
	
	private final int status;
	private final String message;
	private final Object data;
	
	public ResponseBody(int status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public int getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}
}
