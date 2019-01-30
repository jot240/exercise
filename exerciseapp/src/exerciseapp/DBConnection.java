package exerciseapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet resultSet = null;
	private String configPath = System.getProperty("user.dir")+"\\src\\resources\\config.properties";
	
	
	private void connectToDB() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException{
	Properties appProps = new Properties();
	appProps.load(new FileInputStream(configPath));
	String username = appProps.getProperty("username");
	String password =  appProps.getProperty("password");
	String url = "jdbc:mysql://localhost:3306/exercisedb";
	con = DriverManager.getConnection(url, username, password);
	System.out.println("done1");
	}
	
	public ResultSet executeQuery(String query , String tableName, String field) throws Exception{
		try {

		connectToDB();
		stmt = con.createStatement();
		resultSet= stmt.executeQuery("select " + query + " from " + tableName +"." + field);
		return resultSet;
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			close();
		}
	}
	public ResultSet executeQuery(String tableName, String field) throws Exception{
		try {
			connectToDB();
			stmt = con.createStatement();
			resultSet= stmt.executeQuery("select " + field + " from " + tableName );
			return resultSet;
		}
		catch (Exception e) {
			throw e;
		}
				}
	
	
	
	
	  public void close() {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }

	            if (stmt != null) {
	                stmt.close();
	            }

	            if (con != null) {
	                con.close();
	            }
	        } catch (Exception e) {

	        }

	
}
}
