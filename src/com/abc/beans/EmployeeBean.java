package com.abc.beans;

public class EmployeeBean {

	private int eid;
	private String ename, password;

	public EmployeeBean() {
	}

	public EmployeeBean(int eid, String ename, String password) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.password = password;

	}

	
	public EmployeeBean(String ename, String password) {
		super();
		this.ename = ename;
		this.password = password;

	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "EmployeeBean [eid=" + eid + ", ename=" + ename + ", password=" + password + "]";
	}

}