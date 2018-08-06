package EmployeeDAO;

import java.util.ArrayList;

import RequestDAO.RequestModel;

public interface EmployeeDAO {
	
	public EmployeeModel getEmployee(String username, String password);
	public boolean createEmployee(EmployeeModel e);
	public EmployeeModel updateEmployee(EmployeeModel e);
	public ArrayList<EmployeeModel> viewEmployees();
	public boolean makeRequest(RequestModel r);
	public RequestModel approveRequest(int r, int approve, String completed, EmployeeModel e);
	public ArrayList<RequestModel> viewAllRequests();
	public ArrayList<RequestModel> viewMyRequests(int e_id);

}
