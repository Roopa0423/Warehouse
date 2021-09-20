package com.abc.beans;

import java.sql.Date;
import java.time.LocalDate;

public class SupplyProductBean {
	

	private String sname,pname;
	private int sid,pid;
	private Date date;
	private String sstatus;
	private int quantity;
	private int remainingQuantity;

	public SupplyProductBean() {}
	public SupplyProductBean(String sname, String pname,int sid,int pid,Date date,String status,int quantity,int remainingQuantity) {
		super();
		this.sname = sname;
		this.pname=pname;
		this.sid = sid;
		this.pid = pid;
		this.date = date;
		this.sstatus = status;
		this.quantity=quantity;
		this.remainingQuantity = remainingQuantity;
		
	}
//	public SupplyProductBean(String sname2, String pname2, int sid2, int pid2, LocalDate date2, String sstatus2) {
//		// TODO Auto-generated constructor stub
//	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getSid() {
		return sid;
	}
	public int getRemainingQuantity() {
		return remainingQuantity;
	}
	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date2) {
		this.date = date2;
	}
	public String getSstatus() {
		return sstatus;
	}
	public void setSstatus(String sstatus) {
		this.sstatus = sstatus;
	}

}
