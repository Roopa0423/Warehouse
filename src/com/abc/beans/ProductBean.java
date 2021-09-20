package com.abc.beans;

public class ProductBean {

	private String pname;
	private int pid;
	private double pquantity,supplied;

	public ProductBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductBean(String pname, int pid, double pquantity,double supplied) {
		super();
		this.pname = pname;
		this.pid = pid;
		this.pquantity = pquantity;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public double getPquantity() {
		return pquantity;
	}

	public void setPquantity(double pquantity) {
		this.pquantity = pquantity;
	}

	public double getSupplied() {
		return supplied;
	}

	public void setSupplied(double supplied) {
		this.supplied = supplied;
	}

//	public static int save(ProductBean bean) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	
}
