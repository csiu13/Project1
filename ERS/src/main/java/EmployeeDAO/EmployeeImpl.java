package EmployeeDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import RequestDAO.RequestModel;
import Utilities.Connector;


public class EmployeeImpl implements EmployeeDAO {
	
	private static EmployeeImpl ei;
	private EmployeeImpl() {
		
	}
	
	public static EmployeeImpl getEI() {
		if(ei == null) {
			ei = new EmployeeImpl();
		}
		return ei;
	}
	
	@Override
	public EmployeeModel getEmployee(String username, String password) {
		try {
			Connection conn = Connector.getConnection();
			String sql = "select * from employees where username = ? and password = ?";
			
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, username);
			p.setString(2, password);
			
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				return new EmployeeModel(
						rs.getInt("e_id"),
						rs.getString("name"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getInt("age"),
						rs.getInt("img_id"),
						rs.getInt("access")
						);
			}
		} catch(SQLException er) {
			er.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean createEmployee(EmployeeModel e) {
		try {
			Connection conn = Connector.getConnection();
			String sql = "exec add_employee (?, ?, ?, ?, ?, ?)";
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, e.getImg_id());
			cs.setString(2, e.getName());
			cs.setString(3, e.getUsername());
			cs.setString(4, e.getPassword());
			cs.setInt(5, e.getAge());
			cs.setInt(6, e.getAccess());
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			
			cs.execute();
			return cs.getInt(7) == 1;
			
		} catch(SQLException er) {
			er.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployee(EmployeeModel e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean viewEmployee(EmployeeModel e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean makeRequest(EmployeeModel e, RequestModel r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean approveRequest(EmployeeModel e, RequestModel r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean viewPendingRequests(EmployeeModel e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean viewCompletedRequests(EmployeeModel e) {
		// TODO Auto-generated method stub
		return false;
	}

}
