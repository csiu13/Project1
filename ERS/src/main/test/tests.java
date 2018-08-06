import org.junit.Test;
import EmployeeDAO.EmployeeModel;
import EmployeeDAO.EmployeeService;
public class tests {
	
	@Test
	public void test1() {
		EmployeeModel e = new EmployeeModel(-1, "Temp", "temp", "password", 12, 0, 0);
		//System.out.println(EmployeeService.getES().createEmployee(e));
	}

}
