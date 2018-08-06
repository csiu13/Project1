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
		if(request.getParameter("check") != null) {
			response.getWriter().append((String)request.getServletContext().getAttribute("currUser"));
		} else if(request.getParameter("checkList") != null){
			response.getWriter().append((String)request.getServletContext().getAttribute("reqList"));
		} else {
			String res = EmployeeService.handleRequest(request);
			//System.out.println(res);
			if(res.indexOf("/") != -1) {
				request.getRequestDispatcher(res).forward(request, response);
			} else {
				//System.out.println("WHAT");
				response.getWriter().append(res);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
