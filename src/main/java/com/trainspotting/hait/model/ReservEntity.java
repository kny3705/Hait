package com.trainspotting.hait.model;

public class ReservEntity {
	private int pk;
	private int seq;
	private int rstrnt_pk;
	private int customer_pk;
	private int headcount;
	private int process_status;
	
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getRstrnt_pk() {
		return rstrnt_pk;
	}
	public void setRstrnt_pk(int rstrnt_pk) {
		this.rstrnt_pk = rstrnt_pk;
	}
	public int getCustomer_pk() {
		return customer_pk;
	}
	public void setCustomer_pk(int customer_pk) {
		this.customer_pk = customer_pk;
	}
	public int getHeadcount() {
		return headcount;
	}
	public void setHeadcount(int headcount) {
		this.headcount = headcount;
	}
	public int getProcess_status() {
		return process_status;
	}
	public void setProcess_status(int process_status) {
		this.process_status = process_status;
	}
	
}
