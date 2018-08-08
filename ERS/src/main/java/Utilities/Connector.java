package Utilities;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connector {
    public static Connection getConnection() {
		//InputStream in = null;
		
		try {
			// Properties props = new Properties();
			// //System.out.println(System.getProperty("user.dir"));
			// in  = new FileInputStream("C:\\my_git_ripos\\christopher_siu_project1\\ERS\\src\\main\\resources\\connections.properties");
			// props.load(in);
			
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			// String endpoint = props.getProperty("jdbc.url");
			// //System.out.println(endpoint);
			// String username = props.getProperty("jdbc.username");
			// String password = props.getProperty("jdbc.password");
			
			//conn = DriverManager.getConnection(endpoint, username, password);
			conn = DriverManager.getConnection("jdbc:oracle:thin:@siudb.ckqj1sf1jfmd.us-east-1.rds.amazonaws.com:1521:ORCL", "christopher", "password145");

			return conn;
		} catch(Exception e) {
			e.printStackTrace();
		}
		// } finally {
		// 	try {
		// 		in.close();
		// 	} catch (IOException e) {
		// 		e.printStackTrace();
		// 	}
		// }
		
		return null;
	}
}