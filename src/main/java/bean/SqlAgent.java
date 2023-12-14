package bean;

import java.io.PrintWriter;
import java.sql.*;
import bean.User;

public class SqlAgent {
	Connection conn = null;
	Statement stat = null;
	ResultSet res = null;
	String url = null;
	String user = null;
	String pass = null;
	
	public SqlAgent () throws ClassNotFoundException, SQLException {
		// load connector
		Class.forName("com.mysql.cj.jdbc.Driver");
		// default mysql params, change these to yours.
		// don't forget to create db and grant permission!
		this.url = "jdbc:mysql://localhost:3306/jspdemo";
		this.user = "demo";
		this.pass = "demo123!@#";
	}
	
	public SqlAgent (String url, String user, String pass) throws ClassNotFoundException, SQLException {
		// load mysql connector
		Class.forName("com.mysql.cj.jdbc.Driver");
		// save parameters
		this.url = url; this.user = user; this.pass = pass;
	}
	
	public ResultSet executeQuery (String sql) throws SQLException{
		// get connection
		conn = DriverManager.getConnection(url, user, pass);
		// get statement
		stat = conn.createStatement();
		// execute query
		res = stat.executeQuery(sql);
		return res;
	}
	
	public int executeUpdate (String sql) throws SQLException {
		// get connection
		conn = DriverManager.getConnection(url, user, pass);
		// get statement
		stat = conn.createStatement();
		// same as query, but we return updated items count
		stat = conn.createStatement();
		int status = stat.executeUpdate(sql);
		return status;
	}
	
	public void destroy () throws SQLException {
		// close everything
		if (res != null) {
			res.close();
			res = null;
		}
		if (stat != null) {
			stat.close();
			stat = null;
		}
		if (conn != null) {
			conn.close();
			conn = null;
		}
	}
}
