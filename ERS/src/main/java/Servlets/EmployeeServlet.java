package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EmployeeDAO.EmployeeModel;
import EmployeeDAO.EmployeeService;

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EmployeeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int age = Integer.parseInt(request.getParameter("age"));
		EmployeeModel e = new EmployeeModel(-1, request.getParameter("name"), request.getParameter("username"), request.getParameter("password"), age, -1, 0);
		
		response.getWriter().append(EmployeeService.getES().createEmployee(e)+"");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
