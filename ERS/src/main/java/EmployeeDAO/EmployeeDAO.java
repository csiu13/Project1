package EmployeeDAO;

import RequestDAO.RequestModel;

public interface EmployeeDAO {
	
	public EmployeeModel getEmployee(String username, String password);
	public boolean createEmployee(EmployeeModel e);
	public boolean updateEmployee(EmployeeModel e);
	public String viewEmployee(EmployeeModel e);						//This parameter is who is asking to see employee
	public boolean makeRequest(EmployeeModel e, RequestModel r);
	public boolean approveRequest(EmployeeModel e, RequestModel r);
	public String viewPendingRequests(EmployeeModel e);				//This parameter is who is asking to see requests
	public String viewCompletedRequests(EmployeeModel e);				//This parameter is who is asking to see requests

}
