package com.revature.dao;

import java.util.ArrayList;

import com.revature.beans.Form;

public interface FormDAO {
	
	public void insertFormDetails(Form form, int employeeid);
	
	public Form callFormDetails(int applicationid);
	
	public void DSapproval(int employeeid);

    public void DSnonapproval(int employeeid);

    public void DHapproval(int employeeid);

    public void DHnonapproval(int employeeid, String denialReason);

	public ArrayList<Form> callAllFormsds(int employeeId);
	public ArrayList<Form> callAllFormsdh();
}
