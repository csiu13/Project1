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
				ArrayList<EmployeeModel> listE = getES().viewEmployees();
				if(listE != null) {
					LoggingTool.logDebug("VIEW EMPLOYEES SUCCESS");
					String eList = gson.toJson(listE);
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
				
			case("makeRequest.employeeDo"):
				if(getES().makeRequest(request)) {
					LoggingTool.logDebug("REQUEST CREATION SUCCESS");
					return "success";
				} else {
					LoggingTool.logError("REQUEST CREATION ERROR");
					return "error";
				}
			
			case("approveRequest.employeeDo"):
				RequestModel r = getES().approveRequest(request);
				if(r != null) {
					LoggingTool.logDebug("REQUEST APPROVAL SUCCESS");
					return gson.toJson(r);
				} else {
					LoggingTool.logError("REQUEST APPROVAL ERROR");
					return "fail";
				}
				
			case("viewMyRequests.employeeDo"):
				ArrayList<RequestModel> listR = getES().viewMyRequests(request);
				if(listR != null) {
					LoggingTool.logDebug("VIEW MY REQUESTS SUCCESS");
					return gson.toJson(listR);
				} else {
					LoggingTool.logError("VIEW MY REQUESTS ERROR");
					return "fail";
				}
			case("viewAllRequests.employeeDo"):
				listR = getES().viewAllRequests(request);
				if(listR != null) {
					LoggingTool.logDebug("VIEW MY REQUESTS SUCCESS");
					request.getServletContext().setAttribute("reqList", gson.toJson(listR));
					return gson.toJson(listR);
				} else {
					LoggingTool.logError("VIEW MY REQUESTS ERROR");
					return "fail";
				}
			case("viewTheirRequests.employeeDo"):
				listR = getES().viewTheirRequests(request);
				if (listR != null) {
					LoggingTool.logDebug("VIEW THEIR REQUESTS SUCCESS");
					return gson.toJson(listR);
				} else {
					LoggingTool.logError("VIEW THEIR REQUESTS ERROR");
					return "fail";
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
		String name = request.getParameter("name") == "" ? curr.getName() : request.getParameter("name");
		String username = request.getParameter("username") == "" ? curr.getUsername() : request.getParameter("username");
		String password = request.getParameter("password") == "" ? curr.getPassword() : request.getParameter("password");
		int age = request.getParameter("age") == "" ? curr.getAge() : Integer.parseInt(request.getParameter("age"));
//		System.out.println(eid);
//		System.out.println(access);
//		System.out.println(name);
//		System.out.println(username);
//		System.out.println(password);
//		System.out.println(age);
//		
		EmployeeModel e = new EmployeeModel(eid, name, username, password, age, -1, access);
		return EmployeeImpl.getEI().updateEmployee(e);
	}
	public ArrayList<EmployeeModel> viewEmployees() {
		return EmployeeImpl.getEI().viewEmployees();
	}
	public boolean makeRequest(HttpServletRequest request) {
		EmployeeModel curr = gson.fromJson((String)request.getServletContext().getAttribute("currUser"), EmployeeModel.class);
		String requested = request.getParameter("requested");
		String reason = request.getParameter("reason");
		double amount = Double.parseDouble(request.getParameter("amount"));
//		System.out.println(requested);
//		System.out.println(reason);
//		System.out.println(amount);
		RequestModel r = new RequestModel(-1, -1, "", curr.getName(), curr.getE_id(), requested, null, reason, amount, 0);
		return EmployeeImpl.getEI().makeRequest(r);
	}
	public RequestModel approveRequest(HttpServletRequest request) {
		EmployeeModel curr = gson.fromJson((String)request.getServletContext().getAttribute("currUser"), EmployeeModel.class);
//		System.out.println(request.getParameter("r_id"));
//		System.out.println(request.getParameter("approve"));
		int r_id = Integer.parseInt(request.getParameter("r_id"));
		int approve = Integer.parseInt(request.getParameter("approve"));
		String completed = request.getParameter("completed");
		return EmployeeImpl.getEI().approveRequest(r_id, approve, completed, curr);
	}
	public ArrayList<RequestModel> viewMyRequests(HttpServletRequest request) {
		EmployeeModel curr = gson.fromJson((String)request.getServletContext().getAttribute("currUser"), EmployeeModel.class);
		return EmployeeImpl.getEI().viewMyRequests(curr.getE_id());
	}
	public ArrayList<RequestModel> viewAllRequests(HttpServletRequest request) {
		return EmployeeImpl.getEI().viewAllRequests();
	}
	public ArrayList<RequestModel> viewTheirRequests(HttpServletRequest request) {
		int e_id = Integer.parseInt(request.getParameter("e_id"));
		return EmployeeImpl.getEI().viewMyRequests(e_id);
	}

}
