package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import EmployeeDAO.EmployeeModel;
import EmployeeDAO.EmployeeService;

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EmployeeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getRequestURI());
		//response.getWriter().append(EmployeeService.handleRequest(request));
		//response.getWriter().append(request.getSession().getAttribute("currEmployee").toString());
		if(request.getParameter("check") != null) {
			response.getWriter().append(request.getServletContext().getAttribute("currUser").toString());
		} else {
			request.getRequestDispatcher(EmployeeService.handleRequest(request)).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
