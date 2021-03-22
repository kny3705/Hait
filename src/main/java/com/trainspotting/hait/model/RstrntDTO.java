package com.trainspotting.hait.model;

public class RstrntDTO extends RstrntEntity {
	private String reset_pw;
	@Override
	public String toString() {
		return "RstrntDTO [reset_pw=" + reset_pw + ", toString()=" + super.toString() + "]";
	}
	public String getReset_pw() {
		return reset_pw;
	}
	public void setReset_pw(String reset_pw) {
		this.reset_pw = reset_pw;
	}
}