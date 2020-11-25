package com.revature.beans;

public class Reimbursement {
	
	private int applicationid;
	private String submittedon; //BE WARY OF DATE FORMAT, THIS MAY NEED REWORK
	private int employeeid;
	private String educationtype;
	private boolean urgent;
	
	public Reimbursement() {
		super();
	}
	public Reimbursement(int applicationid, String submittedon, int employeeid, String educationtype, boolean urgent) {
		super();
		this.applicationid = applicationid;
		this.submittedon = submittedon;
		this.employeeid = employeeid;
		this.educationtype = educationtype;
		this.urgent = urgent;
	}
	public int getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(int applicationid) {
		this.applicationid = applicationid;
	}
	public String getSubmittedon() {
		return submittedon;
	}
	public void setSubmittedon(String submittedon) {
		this.submittedon = submittedon;
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public String getEducationtype() {
		return educationtype;
	}
	public void setEducationtype(String educationtype) {
		this.educationtype = educationtype;
	}
	public boolean isUrgent() {
		return urgent;
	}
	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}
}
