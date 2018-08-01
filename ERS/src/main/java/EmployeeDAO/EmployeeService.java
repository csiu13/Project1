package EmployeeDAO;

import javax.servlet.http.HttpServletRequest;

import Logger.LoggingTool;
import RequestDAO.RequestModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EmployeeService {
	
	private static EmployeeService es;
	private static GsonBuilder builder;
	private static Gson gson;
	private EmployeeService() {
		builder = new GsonBuilder();
		gson = builder.create();
	}
	
	public static EmployeeService getES() {
		if(es == null) {
			es = new EmployeeService();
		}
		return es;
	}
	
	public static String handleRequest(HttpServletRequest request) {
		String service = request.getRequestURI().replace("/ERS/html/", "");
		//System.out.println(service);
		switch(service) {
		
			case("createEmployee.employeeDo"):
				if(!getES().createEmployee(request)) {
					//System.out.println("CREATION ERROR");
					LoggingTool.logError("CREATION ERROR");
					return "CREATION ERROR";
				} else {
					//System.out.println("CREATION SUCCESS");
					LoggingTool.logDebug("CREATION SUCCESS");
					return "CREATION SUCCESS";
				}
			
			case("login.employeeDo"):
				EmployeeModel e = getES().getEmployee(request);
				if(e != null) {
					LoggingTool.logDebug("LOGIN SUCCESS");
					request.getServletContext().setAttribute("currUser", gson.toJson(e));
					return "main.html";
				} else {
					LoggingTool.logDebug("LOGIN ERROR");
					return "LOGIN FAIL";
				}
				
			case("viewAllEmployees.employeeDo"):
				
		
		}
		
		return "";
	}
	
	public EmployeeModel getEmployee(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		return EmployeeImpl.getEI().getEmployee(username, password);
	}
	public boolean createEmployee(HttpServletRequest request) {
		int age = Integer.parseInt(request.getParameter("age"));
		EmployeeModel e = new EmployeeModel(-1, request.getParameter("name"), request.getParameter("username"), request.getParameter("password"), age, -1, 0);
		return EmployeeImpl.getEI().createEmployee(e);
	}
	public boolean updateEmployee(EmployeeModel e) {
		return EmployeeImpl.getEI().updateEmployee(e);
	}
	public String viewEmployee(EmployeeModel e) {
		return EmployeeImpl.getEI().viewEmployee(e);
	}
	public boolean makeRequest(EmployeeModel e, RequestModel r) {
		return EmployeeImpl.getEI().makeRequest(e, r);
	}
	public boolean approveRequest(EmployeeModel e, RequestModel r) {
		return EmployeeImpl.getEI().approveRequest(e, r);
	}
	public String viewPendingRequests(EmployeeModel e) {
		return EmployeeImpl.getEI().viewPendingRequests(e);
	}
	public String viewCompletedRequests(EmployeeModel e) {
		return EmployeeImpl.getEI().viewCompletedRequests(e);
	}

}
