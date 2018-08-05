package EmployeeDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
//			System.out.println(username);
//			System.out.println(password);
			
			ResultSet rs = p.executeQuery();
			if(rs == null) {
				return null;
			}
			while(rs.next()) {
				return new EmployeeModel(
						rs.getInt("e_id"),
						rs.getString("name"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getInt("age"),
						rs.getInt("i_id"),
						rs.getInt("access_level")
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
			String sql = "call add_employee (?, ?, ?, ?, ?, ?, ?)";
			
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
	public EmployeeModel updateEmployee(EmployeeModel e) {
		try {
			Connection conn = Connector.getConnection();
			String sql = "update employees set i_id=?, name=?, username=?, password=?, age=?, access_level=? where e_id=?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getName());
			ps.setString(2, e.getUsername());
			ps.setString(3, e.getPassword());
			ps.setInt(4, e.getAge());
			ps.setInt(5, e.getAccess());
			ps.setInt(6, e.getImg_id());
			
			return ps.executeQuery() != null ? e : null;
			
		} catch(SQLException er) {
			er.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<EmployeeModel> viewEmployees() {
		try {
			Connection conn = Connector.getConnection();
			String sql = "select * from employees order by e_id asc";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs == null) {
				return null;
			}
			ArrayList<EmployeeModel> list = new ArrayList<EmployeeModel>();
			while(rs.next()) {
				list.add(new EmployeeModel(
						rs.getInt("e_id"),
						rs.getString("name"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getInt("age"),
						rs.getInt("i_id"),
						rs.getInt("access_level")
						));
			}
			
			return list; 
			
		} catch(SQLException er) {
			er.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean makeRequest(EmployeeModel e, RequestModel r) {
		return false;
	}

	@Override
	public boolean approveRequest(EmployeeModel e, RequestModel r) {
		return false;
	}

	@Override
	public String viewPendingRequests(EmployeeModel e) {
		return "";
	}

	@Override
	public String viewCompletedRequests(EmployeeModel e) {
		return "";
	}

}
