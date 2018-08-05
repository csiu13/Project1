package EmployeeDAO;

import java.util.ArrayList;

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
		String service = request.getRequestURI().replace("/ERS/", "");
		int index = service.indexOf("?");
		if(index > -1) {
			service = service.substring(0, index);
		}
		//System.out.println(service);
		switch(service) {
		
			case("createEmployee.employeeDo"):
				EmployeeModel e = getES().createEmployee(request);
				if(e == null) {
					//System.out.println("CREATION ERROR");
					LoggingTool.logError("CREATION ERROR");
					return "html/error.html";
				} else {
					//System.out.println("CREATION SUCCESS");
					LoggingTool.logDebug("CREATION SUCCESS");
					request.getServletContext().setAttribute("currUser", gson.toJson(e));
					return "html/main.html";
				}
			
			case("login.employeeDo"):
				e = getES().getEmployee(request);
				if(e != null) {
					LoggingTool.logDebug("LOGIN SUCCESS");
					request.getServletContext().setAttribute("currUser", gson.toJson(e));
					return "html/main.html";
				} else {
					LoggingTool.logDebug("LOGIN ERROR");
					return "fail";
				}
				
			case("viewEmployees.employeeDo"):
				ArrayList<EmployeeModel> list = getES().viewEmployees();
				if(list != null) {
					LoggingTool.logDebug("VIEW EMPLOYEES SUCCESS");
					String eList = gson.toJson(list);
					request.getServletContext().setAttribute("employeeList", eList);
					return eList;
				} else {
					LoggingTool.logError("VIEW EMPLOYEES ERROR");
					return "html/error.html";
				}
			
			case("updateEmployee.employeeDo"):
				e = getES().updateEmployee(request);
				if(e != null) {
					request.getServletContext().setAttribute("currUser", gson.toJson(e));
					LoggingTool.logDebug("UPDATE SUCCESS");
					return gson.toJson(e);
				} else {
					LoggingTool.logError("UPDATE ERROR");
					return "html/error.html";
				}
		
		}
		
		return "";
	}
	
	public EmployeeModel getEmployee(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		return EmployeeImpl.getEI().getEmployee(username, password);
	}
	public EmployeeModel createEmployee(HttpServletRequest request) {
		int age = Integer.parseInt(request.getParameter("age"));
		EmployeeModel e = new EmployeeModel(-1, request.getParameter("name"), request.getParameter("username"), request.getParameter("password"), age, -1, 0);
		return EmployeeImpl.getEI().createEmployee(e) ? e : null;
	}
	public EmployeeModel updateEmployee(HttpServletRequest request) {
		EmployeeModel curr = gson.fromJson((String)request.getServletContext().getAttribute("currUser"), EmployeeModel.class);
		int eid = curr.getE_id();
		int access = curr.getAccess();
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int age = Integer.parseInt(request.getParameter("age"));
		EmployeeModel e = new EmployeeModel(eid, name, username, password, age, -1, access);
		return EmployeeImpl.getEI().updateEmployee(e);
	}
	public ArrayList<EmployeeModel> viewEmployees() {
		return EmployeeImpl.getEI().viewEmployees();
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
