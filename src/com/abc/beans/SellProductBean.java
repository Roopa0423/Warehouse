package com.abc.beans;

import java.sql.Date;

public class SellProductBean {
	int  pid,seid,pquantity;
	Date date;

	public SellProductBean(int pid,int seid,int pquantity,Date date) {
		
		this.pid=pid;
		this.seid=seid;
		this.pquantity=pquantity;
		this.date=date;
	}

	public SellProductBean() {
		// TODO Auto-generated constructor stub
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getSeid() {
		return seid;
	}

	public void setSeid(int seid) {
		this.seid = seid;
	}

	public int getPquantity() {
		return pquantity;
	}

	public void setPquantity(int pquantity) {
		this.pquantity = pquantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
