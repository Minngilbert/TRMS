package bs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {
	
	public static void process(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String  s = request.getRequestURI();
		
		switch(s) {
		
		case "/Project1/api/login":
			AuthenticateController.login(request,response);
			break;
			
		case "/Project1/api/home":
			HomeController.getHomePage(request, response);
			break;
		case "/Project1/api/logout":
			AuthenticateController.logout(request,response);
			break;
		case "/Project1/api/dhView":
			ReimbursementController.getDHView(request, response);
			break;
		case "/Project1/api/approvalDH":
			ReimbursementController.approveDH(request, response);
			break;
		case "/Project1/api/denyDH":
			ReimbursementController.denyDH(request, response);
			break;
//		case "/Project1/api/empView":
//			ReimbursementController.getEmployeeView(request,response);
//			break;
		case "/Project1/api/RAIFE"://this is for dh 
			ReimbursementController.getrequestMoreInfo(request, response);
			break;			
		case "/Project1/api/EMPTrms":
			HomeController.getTrmsForm(request, response);
			break;
		case "/Project1/api/homef":
			HomeController.submitTrmsForm(request, response);
			break;
		case "/Project1/api/notifyEMP":
			ReimbursementController.notifyMoreInformation(request, response);
			break;
		case "/Project1/api/notifyEMPapp":
			ReimbursementController.notifyReimbursementApp(request, response);
			break;
		case "/Project1/api/BCView":
			ReimbursementController.getBCView(request, response);
			break;
		case "/Project1/api/approvalBC":
			ReimbursementController.approveBC(request, response);
			break;
		case "/Project1/api/denyBC":
			ReimbursementController.denyBC(request, response);
			break;
		case "/Project1/api/RAIFEBC":
			ReimbursementController.getRequestMoreInfoBC(request, response);
			break;
		case "/Project1/api/DSView":
			ReimbursementController.getDSView(request, response);
			break;
		case "/Project1/api/approvalDS":
			ReimbursementController.approveDS(request, response);
			break;
		case "/Project1/api/denyDS":
			ReimbursementController.denyDS(request, response);
			break;
		case "/Project1/api/RAIFEDS":
			ReimbursementController.getRequestMoreInfoDS(request, response);
			break;
		default:
			/*
			 * if there is no session send user to the login 
			 * otherwise send them to the home page
			 */
			if(request.getSession(false) == null || request.getSession().getAttribute("CurrUser") == null) {
				System.out.println("RequestHelper: reroute to login if no session or curruser is null: "+ request.getSession(false));
				RequestDispatcher rd = request.getRequestDispatcher("/LoginEMP.html");
				rd.forward(request,response);
			}
			else {
				System.out.println("RequestHelper: reroute to home if there is a session: "+request.getSession(false));
				RequestDispatcher rd = request.getRequestDispatcher("/HomeEMP.html");
				rd.forward(request,response);
			}
		}
	}
}
