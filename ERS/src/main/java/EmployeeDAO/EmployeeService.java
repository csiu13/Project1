package EmployeeDAO;

import RequestDAO.RequestModel;

public class EmployeeService {
	
	private static EmployeeService es;
	private EmployeeService() {
		
	}
	
	public static EmployeeService getES() {
		if(es == null) {
			es = new EmployeeService();
		}
		return es;
	}
	
	public EmployeeModel getEmployee(String username, String password) {
		return EmployeeImpl.getEI().getEmployee(username, password);
	}
	public boolean createEmployee(EmployeeModel e) {
		return EmployeeImpl.getEI().createEmployee(e);
	}
	public boolean updateEmployee(EmployeeModel e) {
		return EmployeeImpl.getEI().updateEmployee(e);
	}
	public boolean viewEmployee(EmployeeModel e) {
		return EmployeeImpl.getEI().viewEmployee(e);
	}
	public boolean makeRequest(EmployeeModel e, RequestModel r) {
		return EmployeeImpl.getEI().makeRequest(e, r);
	}
	public boolean approveRequest(EmployeeModel e, RequestModel r) {
		return EmployeeImpl.getEI().approveRequest(e, r);
	}
	public boolean viewPendingRequests(EmployeeModel e) {
		return EmployeeImpl.getEI().viewPendingRequests(e);
	}
	public boolean viewCompletedRequests(EmployeeModel e) {
		return EmployeeImpl.getEI().viewCompletedRequests(e);
	}

}
