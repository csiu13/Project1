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
			String sql = "call create_request (?, ?, ?, ?, ?, ?)";
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, -1);
			cs.setInt(2, r.getE_id());
			cs.setString(3, r.getRequested());
			cs.setDouble(4, r.getAmount());
			cs.setString(5, r.getReason());
			cs.registerOutParameter(6, java.sql.Types.INTEGER);
			
			cs.execute();
			return cs.getInt(6) == 1;
			
		} catch(SQLException er) {
			er.printStackTrace();
		}
		return false;
	}

	@Override
	public RequestModel approveRequest(int r_id, int approve, String completed, EmployeeModel e) {
		try {
			Connection conn = Connector.getConnection();
			String sql = "select requests.*, employees.name from requests"
					+ " left join employees on requests.e_id = employees.e_id where r_id=?";
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setInt(1, r_id);
			ResultSet rs = ps1.executeQuery();
			if(rs == null) {
				//System.out.println("Empty result");
				return null;
			}
			rs.next();
			//System.out.println(rs.getInt("m_id"));
			if(rs.getInt("m_id") == -1 && rs.getInt("status") == 0) {
				System.out.println("into second sql");
				sql = "update requests set m_id=?, status=?, date_completed=? where r_id=?";

				PreparedStatement ps2 = conn.prepareStatement(sql);
				ps2.setInt(1, e.getE_id());
				ps2.setInt(2, approve);
				ps2.setString(3, completed);
				ps2.setInt(4, r_id);
				
				ResultSet rs1 = ps2.executeQuery();
				while(rs1.next()) {
					return new RequestModel(
							rs.getInt("r_id"),
							rs.getInt("m_id"),
							e.getName(),
							rs.getString("name"),
							rs.getInt("e_id"),
							rs.getString("date_requested"),
							rs.getString("date_completed") == null ? "none" : rs.getString("date_completed"),
							rs.getString("reason"),
							rs.getDouble("amount"),
							rs.getInt("status")
							);
				}
			} else {
				System.out.println("welp");
			}
			
		} catch(SQLException er) {
			er.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<RequestModel> viewMyRequests(int e_id) {
		try {
			Connection conn = Connector.getConnection();
			String sql = "select * from ((select requests.*, employees.name as employee from requests "
					+ "left join employees on requests.e_id = employees.e_id) temp1) "
					+ "full join ((select requests.r_id, employees.name as manager from requests "
					+ "inner join employees on requests.m_id = employees.e_id) temp2) on temp1.r_id = temp2.r_id where e_id=?"
					+ "order by temp1.r_id asc";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e_id);
			
			ResultSet rs = ps.executeQuery();
			ArrayList<RequestModel> list = new ArrayList<RequestModel>();
			
			while(rs.next()) {
				list.add(new RequestModel(
							rs.getInt("r_id"),
							rs.getInt("m_id"),
							rs.getString("manager"),
							rs.getString("employee"),
							rs.getInt("e_id"),
							rs.getString("date_requested"),
							rs.getString("date_completed") == null ? "none" : rs.getString("date_completed"),
							rs.getString("reason"),
							rs.getDouble("amount"),
							rs.getInt("status")
							));
			}
			return list;
			
		} catch (SQLException er) {
			er.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<RequestModel> viewAllRequests() {
		try {
			Connection conn = Connector.getConnection();
			String sql = "select * from ((select requests.*, employees.name as employee from requests "
					+ "left join employees on requests.e_id = employees.e_id) temp1) "
					+ "full join ((select requests.r_id, employees.name as manager from requests "
					+ "inner join employees on requests.m_id = employees.e_id) temp2) on temp1.r_id = temp2.r_id "
					+ "order by temp1.r_id asc";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			ArrayList<RequestModel> list = new ArrayList<RequestModel>();
			
			while(rs.next()) {
				list.add(new RequestModel(
							rs.getInt("r_id"),
							rs.getInt("m_id"),
							rs.getString("manager"),
							rs.getString("employee"),
							rs.getInt("e_id"),
							rs.getString("date_requested"),
							rs.getString("date_completed") == null ? "none" : rs.getString("date_completed"),
							rs.getString("reason"),
							rs.getDouble("amount"),
							rs.getInt("status")
							));
			}
			return list;
			
		} catch (SQLException er) {
			er.printStackTrace();
		}
		
		return null;
	}

}
