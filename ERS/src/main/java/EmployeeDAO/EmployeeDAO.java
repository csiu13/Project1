package EmployeeDAO;

import java.util.ArrayList;

import RequestDAO.RequestModel;

public interface EmployeeDAO {
	
	public EmployeeModel getEmployee(String username, String password);
	public boolean createEmployee(EmployeeModel e);
	public EmployeeModel updateEmployee(EmployeeModel e);
	public ArrayList<EmployeeModel> viewEmployees();
	public boolean makeRequest(EmployeeModel e, RequestModel r);
	public boolean approveRequest(EmployeeModel e, RequestModel r);
	public String viewPendingRequests(EmployeeModel e);
	public String viewCompletedRequests(EmployeeModel e);

}
