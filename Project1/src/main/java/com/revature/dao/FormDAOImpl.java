package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.Form;
import com.revature.beans.Reimbursement;
import com.revature.util.ConnFactory;

public class FormDAOImpl implements FormDAO {

public static ConnFactory cu= ConnFactory.getInstance();
	
	public void  insertFormDetails(Form form, int employeeid) {
		Connection cnn = cu.getConnection();


		try{	
			String sql = "INSERT INTO formdetails (applicationid, formfirstname, formlastname, formeventname, formeventcost, formeventstartdate, formeventstarttime, formeventendtime, formeventaddress, formeventcity, formeventstate, formeventzip, formgradedformat, gradecutoff, formdescription) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";		
			PreparedStatement ps = cnn.prepareStatement(sql);
			
			ps.setInt(1, ReimbursementImpl.getApplicationID(employeeid)); //check
			ps.setString(2, form.getFormFirstName());
			ps.setString(3, form.getFormLastName());
			ps.setString(4, form.getFormEventName());
			ps.setInt(5, form.getFormEventCost());
			ps.setString(6, form.getFormEventStartDate());
			ps.setString(7, form.getFormEventStartTime());
			ps.setString(8, form.getFormEventEndTime());
			ps.setString(9, form.getFormEventAddress());
			ps.setString(10, form.getFormEventCity());
			ps.setString(11, form.getFormEventState());
			ps.setInt(12, form.getFormEventZip());
			ps.setString(13, form.getFormGradedFormat());
			ps.setString(14, form.getFormGradeCO());
			ps.setString(15, form.getFormDescription());
			
			ps.executeUpdate();
			 
		}catch (SQLException sqle) {
			sqle.printStackTrace();
	}
}



	public Form callFormDetails(int employeeid) {//fix this due to new variables
			Connection cnn = cu.getConnection();
			Form form = new Form();
			
			try{	
				//String sql = "SELECT * FROM formdetails WHERE dsapprovalflag=? and applicationid=(SELECT applicationId from reimbursement where employeeid= ?)";		
				String sql = "SELECT * FROM formdetails WHERE dsapprovalflag = 'Pending' AND applicationid = (SELECT * FROM reimbursement WHERE employeeid = (SELECT * FROM employee WHERE reportsto = ?));";		
				PreparedStatement ps = cnn.prepareStatement(sql);
				ps.setString(1, "Pending");
				ps.setInt(2, employeeid);
				
				
				ResultSet rs = ps.executeQuery();		
				
				while(rs.next()){
					
					form.setFormID(rs.getInt("formID"));
					form.setFormApplicationID(rs.getInt("applicationid"));
					form.setFormFirstName(rs.getString("formfirstname"));
					form.setFormLastName(rs.getString("formlastname"));
					form.setFormEventName(rs.getString("formeventname"));
					form.setFormEventCost(rs.getInt("formeventcost"));
					form.setFormEventStartDate(rs.getString("formeventstartdate"));
					form.setFormEventAddress(rs.getString("formeventaddress"));
					form.setFormEventCity(rs.getString("formeventcity"));
					form.setFormEventState(rs.getString("formeventstate"));
					form.setFormEventZip(rs.getInt("formeventzip"));
					form.setFormGradedFormat(rs.getString("formgradedformat"));
					form.setFormDescription(rs.getString("formdescription"));
		
				}
				
					
			}catch (SQLException sqle) {
				sqle.printStackTrace();
			}	
			return form;
		
		}
	
	@Override
	public ArrayList<Form> callAllFormsdh() {
			Connection cnn = cu.getConnection();
			ArrayList<Form> forms = new ArrayList<Form>();
			
			try{	
				String sql = "SELECT * FROM formdetails where dhapprovalflag = 'Pending'";		
				PreparedStatement ps = cnn.prepareStatement(sql);				
	
				ResultSet rs = ps.executeQuery();		
				Form form = null;
				while(rs.next()){
					form = new Form();
					form.setFormID(rs.getInt("formID"));
					form.setFormApplicationID(rs.getInt("applicationid"));
					form.setFormFirstName(rs.getString("formfirstname"));
					form.setFormLastName(rs.getString("formlastname"));
					form.setFormEventName(rs.getString("formeventname"));
					form.setFormEventCost(rs.getInt("formeventcost"));
					form.setFormEventStartDate(rs.getString("formeventstartdate"));
					form.setFormEventAddress(rs.getString("formeventaddress"));
					form.setFormEventCity(rs.getString("formeventcity"));
					form.setFormEventState(rs.getString("formeventstate"));
					form.setFormEventZip(rs.getInt("formeventzip"));
					form.setFormGradedFormat(rs.getString("formgradedformat"));
					form.setFormDescription(rs.getString("formdescription"));
					forms.add(form);
				}					
			}catch (SQLException sqle) {
				sqle.printStackTrace();
			}	
			return forms;		
		}
	public ArrayList<Form> callAllFormsBC() {
		Connection cnn = cu.getConnection();
		ArrayList<Form> forms = new ArrayList<Form>();
		
		try{	
			String sql = "SELECT * FROM formdetails where bencoapprovalflag = 'Pending'";		
			PreparedStatement ps = cnn.prepareStatement(sql);				

			ResultSet rs = ps.executeQuery();		
			Form form = null;
			while(rs.next()){
				form = new Form();
				form.setFormID(rs.getInt("formID"));
				form.setFormApplicationID(rs.getInt("applicationid"));
				form.setFormFirstName(rs.getString("formfirstname"));
				form.setFormLastName(rs.getString("formlastname"));
				form.setFormEventName(rs.getString("formeventname"));
				form.setFormEventCost(rs.getInt("formeventcost"));
				form.setFormEventStartDate(rs.getString("formeventstartdate"));
				form.setFormEventAddress(rs.getString("formeventaddress"));
				form.setFormEventCity(rs.getString("formeventcity"));
				form.setFormEventState(rs.getString("formeventstate"));
				form.setFormEventZip(rs.getInt("formeventzip"));
				form.setFormGradedFormat(rs.getString("formgradedformat"));
				form.setFormDescription(rs.getString("formdescription"));
				forms.add(form);
			}					
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}	
		return forms;		
	}
	
	@Override
	public ArrayList<Form> callAllFormsds(int employeeId) {
			Connection cnn = cu.getConnection();
			ArrayList<Form> forms = new ArrayList<Form>();
			
			try{	
				String sql = "SELECT * FROM formdetails WHERE dsapprovalflag = 'Pending' AND applicationid = (SELECT applicationid FROM reimbursement WHERE employeeid = (SELECT employeeid FROM employee WHERE reportsto = ?))";		
				PreparedStatement ps = cnn.prepareStatement(sql);				
				ps.setInt(1, employeeId);
				ResultSet rs = ps.executeQuery();
				
				Form form = null;
				while(rs.next()){
					form = new Form();
					form.setFormID(rs.getInt("formID"));
					form.setFormApplicationID(rs.getInt("applicationid"));
					form.setFormFirstName(rs.getString("formfirstname"));
					form.setFormLastName(rs.getString("formlastname"));
					form.setFormEventName(rs.getString("formeventname"));
					form.setFormEventCost(rs.getInt("formeventcost"));
					form.setFormEventStartDate(rs.getString("formeventstartdate"));
					form.setFormEventAddress(rs.getString("formeventaddress"));
					form.setFormEventCity(rs.getString("formeventcity"));
					form.setFormEventState(rs.getString("formeventstate"));
					form.setFormEventZip(rs.getInt("formeventzip"));
					form.setFormGradedFormat(rs.getString("formgradedformat"));
					form.setFormDescription(rs.getString("formdescription"));
					forms.add(form);
				}					
			}catch (SQLException sqle) {
				sqle.printStackTrace();
			}	
			return forms;		
		}
	
	@Override
    public void DSapproval(int formId) {
        Connection cnn = cu.getConnection();      
        try{
        	String sql = "update formdetails set dsapprovalflag = 'Approve' where formid = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, formId);
            ps.executeUpdate();
            
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }
}

    @Override
    public void DSnonapproval(int formId) {
        Connection cnn = cu.getConnection();
        
        try{
        	String sql = "update formdetails set dsapprovalflag = 'Denied' where formid = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, formId);
            ps.executeUpdate();
            
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void DHapproval(int formId) {
        Connection cnn = cu.getConnection();
      
        try{
            String sql = "update formdetails set dhapprovalflag = 'Approved' where formid = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, formId);
            ps.executeUpdate();
            
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public void BCapproval(int formId) {
        Connection cnn = cu.getConnection();
      
        try{
            String sql = "update formdetails set bencoapprovalflag = 'Approved' where formid = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, formId);
            ps.executeUpdate();
            
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void DHnonapproval(int formId, String denialReason) {      
        Connection cnn = cu.getConnection();
        
        try{
        	String sql = "UPDATE formdetails SET dhapprovalflag = 'Denied', denialreason = ? WHERE formid = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, denialReason);
            ps.setInt(2, formId);
            ps.executeUpdate();
            
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public void BCnonapproval(int formId, String denialReason) {      
        Connection cnn = cu.getConnection();      
        try{
        	String sql = "UPDATE formdetails SET bencoapprovalflag = 'Denied', denialreason = ? WHERE formid = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, denialReason);
            ps.setInt(2, formId);
            ps.executeUpdate();
            
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

	public ArrayList<Form> callAllFormsDS() {
		Connection cnn = cu.getConnection();
		ArrayList<Form> forms = new ArrayList<Form>();
		
		try{	
			String sql = "SELECT * FROM formdetails where dsapprovalflag = 'Pending'";		
			PreparedStatement ps = cnn.prepareStatement(sql);				

			ResultSet rs = ps.executeQuery();		
			Form form = null;
			while(rs.next()){
				form = new Form();
				form.setFormID(rs.getInt("formID"));
				form.setFormApplicationID(rs.getInt("applicationid"));
				form.setFormFirstName(rs.getString("formfirstname"));
				form.setFormLastName(rs.getString("formlastname"));
				form.setFormEventName(rs.getString("formeventname"));
				form.setFormEventCost(rs.getInt("formeventcost"));
				form.setFormEventStartDate(rs.getString("formeventstartdate"));
				form.setFormEventAddress(rs.getString("formeventaddress"));
				form.setFormEventCity(rs.getString("formeventcity"));
				form.setFormEventState(rs.getString("formeventstate"));
				form.setFormEventZip(rs.getInt("formeventzip"));
				form.setFormGradedFormat(rs.getString("formgradedformat"));
				form.setFormDescription(rs.getString("formdescription"));
				forms.add(form);
			}					
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}	
		return forms;	
	}

	public void DSnonapproval(int formid, String reason) {
        Connection cnn = cu.getConnection();      
        try{
        	String sql = "UPDATE formdetails SET dsapprovalflag = 'Denied', denialreason = ? WHERE formid = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, reason);
            ps.setInt(2, formid);
            ps.executeUpdate();
            
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }
	}



	public String getBencoApproval(int applicationId) throws SQLException {
		Connection cnn = cu.getConnection();
		String sql = "SELECT * FROM formdetails where applicationid = ?";		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setInt(1, applicationId);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			return rs.getString("bencoapprovalflag");
		}
		return null;			
	}
}

