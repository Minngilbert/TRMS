package bs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.beans.Employee;
import com.revature.beans.Form;
import com.revature.dao.FormDAOImpl;
import com.revature.dao.ReimbursementImpl;


public class ReimbursementController {
	
	public static void getEmployeeView(HttpServletRequest request, HttpServletResponse response) {
			Employee emp = (Employee) request.getSession().getAttribute("CurrUser");
			FormDAOImpl fdi = new FormDAOImpl();
			ArrayList<Form> pendingformsDS = fdi.callAllFormsds(emp.getEmployeeId());
			
			System.out.println("ReimbursementController getEmpolyeeView: table set up");
			System.out.println(GenerateJSONFormList(pendingformsDS));	
			
			try {
				response.setContentType("text/html");
				response.getWriter().write(GenerateJSONFormList(pendingformsDS));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public static String GenerateJSONempForm(Form form) {
		String json = new Gson().toJson(form);		
		return json;
	}
	
	public static String GenerateJSONFormList(List<Form> forms) {
		String json = new Gson().toJson(forms);		
		return json;
	}
	
	public static void getDHView(HttpServletRequest request, HttpServletResponse response) {
			FormDAOImpl ri = new FormDAOImpl();
			ArrayList<Form> empforms = ri.callAllFormsdh();
			
			System.out.println(empforms.toString());	
			System.out.println("ReimbursementControlle getSupViewr: table set up");
			System.out.println(GenerateJSONFormList(empforms));	
			
			try {
				response.setContentType("text/html");
				response.getWriter().write(GenerateJSONFormList(empforms));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void approveDH(HttpServletRequest request, HttpServletResponse response) {
		String formIdht = request.getParameter("eid1");
		int formId = Integer.parseInt(formIdht);
		FormDAOImpl fdi = new FormDAOImpl();
		fdi.DHapproval(formId);	
		System.out.println("ReimbursementController, the form id being approved is: "+formId);
		try {
			RequestDispatcher rd = request.getRequestDispatcher("/HomeDH.html");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void denyDH(HttpServletRequest request, HttpServletResponse response) {
		String employeeId = request.getParameter("eid2");
		String reason = request.getParameter("reason");
		int empId = Integer.parseInt(employeeId);
		
		FormDAOImpl fdi = new FormDAOImpl();
		fdi.DHnonapproval(empId, reason);	
		System.out.println(empId);
	try {
		RequestDispatcher rd = request.getRequestDispatcher("/HomeDH.html");
		rd.forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public static void getrequestMoreInfo(HttpServletRequest request, HttpServletResponse response) {
			String appId = request.getParameter("appID");
			int aId = Integer.parseInt(appId);
			ReimbursementImpl ri = new ReimbursementImpl();
			ri.requestInformation(aId);
			
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/HomeDH.html");
				rd.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
	
	public static void getprovideInformation(HttpServletRequest request, HttpServletResponse response) {	
		try {
			RequestDispatcher rd = request.getRequestDispatcher("ChangeME");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void submitrequestMoreInfo(HttpServletRequest request, HttpServletResponse response) {
		String apId = request.getParameter("applicationId");
		int applicationId = Integer.parseInt(apId);
		
		ReimbursementImpl ri = new ReimbursementImpl();
		ri.requestInformation(applicationId);
				
		try {
			PrintWriter pw;
			pw = response.getWriter();
			response.setContentType("text/html");
			//Send JavaScript alert message
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('Submitted');");
			pw.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void submitprovideInformation(HttpServletRequest request, HttpServletResponse response) {	
		String apId = request.getParameter("applicationId");
		String info = request.getParameter("info");
		int applicationId = Integer.parseInt(apId);
		
		ReimbursementImpl ri = new ReimbursementImpl();
		ri.provideInformation(applicationId, info);
		
	
		try {
			PrintWriter pw;
			pw = response.getWriter();
			response.setContentType("text/html");
			//Send JavaScript alert message
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('Submitted');");
			pw.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void notifyMoreInformation(HttpServletRequest request, HttpServletResponse response)  {	
	
		Employee emp = (Employee) request.getSession().getAttribute("CurrUser");
		ReimbursementImpl ri = new ReimbursementImpl();
		try {
			boolean note = ri.getEmpNotifications(emp.getEmployeeId());
			String noti = String.valueOf(note);
			System.out.println(noti);
				PrintWriter pw;
				pw = response.getWriter();
				response.setContentType("text/html");
				pw.write(noti);
				//Send JavaScript alert message
				/*
				 * pw.println("<script type=\"text/javascript\">"); pw.
				 * println("alert('More information is require, please upload in information tab');"
				 * ); pw.println("</script>");
				 */
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public static void getBCView(HttpServletRequest request, HttpServletResponse response) {
		FormDAOImpl ri = new FormDAOImpl();
		ArrayList<Form> empforms = ri.callAllFormsBC();
		
		System.out.println(empforms.toString());	
		System.out.println("ReimbursementControlle getSupViewr: table set up");
		System.out.println(GenerateJSONFormList(empforms));	
		
		try {
			response.setContentType("text/html");
			response.getWriter().write(GenerateJSONFormList(empforms));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void approveBC(HttpServletRequest request, HttpServletResponse response) {
		String formIdht = request.getParameter("eid1");
		int formId = Integer.parseInt(formIdht);
		FormDAOImpl fdi = new FormDAOImpl();
		fdi.BCapproval(formId);	
		System.out.println("ReimbursementController, the form id being approved is: "+formId);
		try {
			RequestDispatcher rd = request.getRequestDispatcher("/HomeBC.html");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void denyBC(HttpServletRequest request, HttpServletResponse response) {
		String employeeId = request.getParameter("eid2");
		String reason = request.getParameter("reason");
		int empId = Integer.parseInt(employeeId);
		
		FormDAOImpl fdi = new FormDAOImpl();
		fdi.BCnonapproval(empId, reason);	
		System.out.println(empId);
		
	try {
		RequestDispatcher rd = request.getRequestDispatcher("/HomeBC.html");
		rd.forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public static void getRequestMoreInfoBC(HttpServletRequest request, HttpServletResponse response) {
		String appId = request.getParameter("appID");
		int aId = Integer.parseInt(appId);
		ReimbursementImpl ri = new ReimbursementImpl();
		ri.requestInformation(aId);
		
		try {
			RequestDispatcher rd = request.getRequestDispatcher("/HomeBC.html");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getDSView(HttpServletRequest request, HttpServletResponse response) {
		FormDAOImpl ri = new FormDAOImpl();
		ArrayList<Form> empforms = ri.callAllFormsDS();
		
		System.out.println(empforms.toString());	
		System.out.println("ReimbursementController get DSview: table set up");
		System.out.println(GenerateJSONFormList(empforms));	
		
		try {
			response.setContentType("text/html");
			response.getWriter().write(GenerateJSONFormList(empforms));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public static void approveDS(HttpServletRequest request, HttpServletResponse response) {
		String formIdht = request.getParameter("eid1");
		int formId = Integer.parseInt(formIdht);
		FormDAOImpl fdi = new FormDAOImpl();
		fdi.DSapproval(formId);	
		System.out.println("ReimbursementController, the form id being approved is: "+formId);
		try {
			RequestDispatcher rd = request.getRequestDispatcher("/HomeDS.html");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void denyDS(HttpServletRequest request, HttpServletResponse response) {
		String employeeId = request.getParameter("eid2");
		String reason = request.getParameter("reason");
		int empId = Integer.parseInt(employeeId);
		
		FormDAOImpl fdi = new FormDAOImpl();
		fdi.DSnonapproval(empId, reason);	
		System.out.println(empId);
		
	try {
		RequestDispatcher rd = request.getRequestDispatcher("/HomeDS.html");
		rd.forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public static void getRequestMoreInfoDS(HttpServletRequest request, HttpServletResponse response) {
		String appId = request.getParameter("appID");
		int aId = Integer.parseInt(appId);
		ReimbursementImpl ri = new ReimbursementImpl();
		ri.requestInformation(aId);
		
		try {
			RequestDispatcher rd = request.getRequestDispatcher("/HomeDS.html");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void notifyReimbursementApp(HttpServletRequest request, HttpServletResponse response) {
		Employee emp = (Employee) request.getSession().getAttribute("CurrUser");
		FormDAOImpl fdi = new FormDAOImpl();
		
		int x = ReimbursementImpl.getApplicationID(emp.getEmployeeId());
	
		try {
			String approval = fdi.getBencoApproval(x);
	
				PrintWriter pw;
				pw = response.getWriter();
				response.setContentType("text/html");
				pw.write(approval);
				//Send JavaScript alert message
				/*
				 * pw.println("<script type=\"text/javascript\">"); pw.
				 * println("alert('More information is require, please upload in information tab');"
				 * ); pw.println("</script>");
				 */
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
