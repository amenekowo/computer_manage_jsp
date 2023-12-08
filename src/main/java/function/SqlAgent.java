package function;

import java.sql.*;

public class SqlAgent {
	Connection conn = null;
	Statement stat = null;
	ResultSet res = null;
	String url = null;
	String user = null;
	String pass = null;
	
	public SqlAgent (String url, String user, String pass) throws ClassNotFoundException, SQLException {
		// load mysql connector
		Class.forName("com.mysql.cj.jdbc.Driver");
		// save parameters
		this.url = url; this.user = user; this.pass = pass;
		// try connecting
		conn = DriverManager.getConnection(url, user, pass);
	}
	
	public ResultSet executeQuery (String sql) throws SQLException{
		// get statement
		stat = conn.createStatement();
		res = stat.executeQuery(sql);
		// DON'T FORGET TO CLOSE!!!!!!
		stat.close();
		return res;
	}
	
	public int executeUpdate (String sql) throws SQLException {
		// same as query, but we return updated items count
		stat = conn.createStatement();
		int status = stat.executeUpdate(sql);
		stat.close();
		return status;
	}
	
	public void openConnection () throws ClassNotFoundException, SQLException {
		// load mysql connector
		Class.forName("com.mysql.cj.jdbc.Driver");
		// get connection
		conn = DriverManager.getConnection(url, user, pass);
	}
	
	public void closeConnection () throws SQLException {
		conn.close();
		conn = null;
	}
}
