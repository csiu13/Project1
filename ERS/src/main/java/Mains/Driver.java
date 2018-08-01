package Mains;

import EmployeeDAO.EmployeeModel;
import EmployeeDAO.EmployeeService;

public class Driver {
	
	public static void main(String[] args) {
		EmployeeModel e = new EmployeeModel(-1, "Temp", "temp", "password", 12, 0, 0);
		//System.out.println(EmployeeService.getES().createEmployee(e));
	}

}
