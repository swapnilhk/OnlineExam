package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private Connection con;
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			new ShowException("Couldn't load JdbcOdbcDriver: "+e.getMessage());			
		}
		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","password");
		}catch(SQLException e){
			new ShowException("Database connection failed: "+e.getMessage());
		}
	}	
	
	public ResultSet executeQuery(String query){
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			new ShowException("Could not execute database query: "+e.getMessage());
		} catch (NullPointerException e){
			new ShowException("Connection Uninitialised");
		}
		return rs;
	}
	
	public void executeUpdate(String query){
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			new ShowException("Could not execute database query: "+e.getMessage());
		} catch (NullPointerException e){
			new ShowException("Connection Uninitialised");
		}
	}
	
}
