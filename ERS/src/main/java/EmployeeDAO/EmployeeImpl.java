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
			ps.setInt(1, e.getImg_id());
			ps.setString(2, e.getName());		
			ps.setString(3, e.getUsername());
			ps.setString(4, e.getPassword());
			ps.setInt(5, e.getAge());
			ps.setInt(6, e.getAccess());
			ps.setInt(7, e.getE_id());
			
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
			String sql = "select * from employees where e_id != -1 order by e_id asc";
			
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
	public boolean makeRequest( RequestModel r) {
		try {
			Connection conn = Connector.getConnection();
			String sql = "call create_request (-1, ?, ?, ?, ?, ?, ?)";
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, r.getE_id());
			cs.setString(2, r.getRequested());
			cs.setDouble(3, r.getAmount());
			cs.setString(4, r.getReason());
			cs.registerOutParameter(5, java.sql.Types.INTEGER);
			
			cs.execute();
			return cs.getInt(5) == 1;
			
		} catch(SQLException er) {
			er.printStackTrace();
		}
		return false;
	}

	@Override
	public RequestModel approveRequest(int r_id, EmployeeModel e) {
		try {
			Connection conn = Connector.getConnection();
			String sql = "select * from requests where r_id=?";
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setInt(1, r_id);
			ResultSet rs = ps1.executeQuery();
			if(rs == null) {
				return null;
			}
			rs.next();
			if(rs.getInt("m_id") == -1 && rs.getInt("status") == 0) {
				sql = "update requests set m_id=?, status=1 where r_id=?";

				PreparedStatement ps2 = conn.prepareStatement(sql);
				ps2.setInt(1, e.getE_id());
				ps2.setInt(2, r_id);
				
				ResultSet rs1 = ps2.executeQuery();
				while(rs.next()) {
					return new RequestModel(
							rs1.getInt("r_id"),
							rs1.getInt("m_id"),
							rs1.getInt("e_id"),
							rs1.getString("requested"),
							rs1.getString("completed"),
							rs1.getString("reason"),
							rs1.getDouble("amount"),
							rs1.getInt("status")
							);
				}
			}
			
		} catch(SQLException er) {
			er.printStackTrace();
		}
		return null;
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
