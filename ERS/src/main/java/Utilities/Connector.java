package Utilities;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connector {
    public static Connection getConnection() {
		InputStream in = null;
		
		try {
			Properties props = new Properties();
			in  = new FileInputStream("src/main/resources/connections.properties");
			props.load(in);
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			String endpoint = props.getProperty("jdbc.url");
			//System.out.println(endpoint);
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			
			conn = DriverManager.getConnection(endpoint, username, password);

			return conn;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}