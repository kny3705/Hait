package com.trainspotting.hait.model;

public class ApplicationEntity {
	private int pk;
	private String owner_nm;
	private String owner_email;
	private String owner_contact;
	private String rstrnt_nm;
	private int city_pk;
	private String more_info;
	private int process_status;
	private String regdate;
	
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public String getOwner_nm() {
		return owner_nm;
	}
	public void setOwner_nm(String owner_nm) {
		this.owner_nm = owner_nm;
	}
	public String getOwner_email() {
		return owner_email;
	}
	public void setOwner_email(String owner_email) {
		this.owner_email = owner_email;
	}
	public String getOwner_contact() {
		return owner_contact;
	}
	public void setOwner_contact(String owner_contact) {
		this.owner_contact = owner_contact;
	}
	public String getRstrnt_nm() {
		return rstrnt_nm;
	}
	public void setRstrnt_nm(String rstrnt_nm) {
		this.rstrnt_nm = rstrnt_nm;
	}
	public int getCity_pk() {
		return city_pk;
	}
	public void setCity_pk(int city_pk) {
		this.city_pk = city_pk;
	}
	public String getMore_info() {
		return more_info;
	}
	public void setMore_info(String more_info) {
		this.more_info = more_info;
	}
	public int getProcess_status() {
		return process_status;
	}
	public void setProcess_status(int process_status) {
		this.process_status = process_status;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
}
