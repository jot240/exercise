package exerciseapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement ppstmt = null;
	private ResultSet resultSet = null;
	private String configPath = System.getProperty("user.dir")+"\\src\\resources\\config.properties";
	
	private void connectToDB() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException{
	Properties appProps = new Properties();
	appProps.load(new FileInputStream(configPath));
	String username = appProps.getProperty("username");
	String password =  appProps.getProperty("password");
	String url = appProps.getProperty("url");
	con = DriverManager.getConnection(url, username, password);
	System.out.println("connected to DB");
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
		finally {
		}
				}
	
	public void CreateExercise( int id, String name, String type, int importance) throws Exception {
		try {
			connectToDB();

			String updateNew = "INSERT INTO exercise(id, name, type, importance) VALUES (?,?,?,?)";
			ppstmt = con.prepareStatement(updateNew);
			ppstmt.setInt(1,id);
			ppstmt.setString(2, name);
			ppstmt.setString(3, type);
			ppstmt.setInt(4, importance);
			ppstmt.executeUpdate();
		
		}
		catch(Exception e){
			throw e;
		}
		finally {
		}
	}
	public void CleanUpExercise() throws Exception {
	//for removing duplicates in db
		try {
		String remove = "DELETE e1 from exercise e1 INNER JOIN exercise e2 WHERE e1.name = e2.name AND e1.id >= e2.id ";
		ppstmt = con.prepareStatement(remove);
		ppstmt.executeUpdate();
		}
		catch(Exception e){
			throw e;
		}
	}
	public int MaxID() throws Exception{
		try {
			int maxID = 1;
			String getMax ="Select MAX(id) as maxID from exercise";
			ppstmt = con.prepareStatement(getMax);
			resultSet = ppstmt.executeQuery();
			while(resultSet.next()) {
				maxID = resultSet.getInt(maxID);
			}
			return maxID;
			
		}
		catch(Exception e){
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
	            if (ppstmt != null) {
	            	ppstmt.close();
	            }
	        } catch (Exception e) {

	        }

	
}
}
