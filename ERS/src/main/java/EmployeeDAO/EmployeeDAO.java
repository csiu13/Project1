package EmployeeDAO;

import java.util.ArrayList;

import RequestDAO.RequestModel;

public interface EmployeeDAO {
	
	public EmployeeModel getEmployee(String username, String password);
	public boolean createEmployee(EmployeeModel e);
	public EmployeeModel updateEmployee(EmployeeModel e);
	public ArrayList<EmployeeModel> viewEmployees();
	public boolean makeRequest(RequestModel r);
	public RequestModel approveRequest(int r, EmployeeModel e);
	public String viewPendingRequests(EmployeeModel e);
	public String viewCompletedRequests(EmployeeModel e);

}
