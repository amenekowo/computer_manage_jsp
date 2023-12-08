package function;

import java.sql.*;

public class SqlAgent {
	Connection conn = null;
	Statement stat = null;
	ResultSet res = null;
	public SqlAgent (String url) throws ClassNotFoundException, SQLException {
		// load mysql connector
		Class.forName("com.mysql.cj.jdbc.Driver");
		// try connecting
		conn = DriverManager.getConnection(url);
		// get statement
		stat = conn.createStatement();
	}
		
	public SqlAgent (String url, String user, String pass) throws ClassNotFoundException, SQLException {
		// load mysql connector
		Class.forName("com.mysql.cj.jdbc.Driver");
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
		stat = conn.createStatement();
		int status = stat.executeUpdate(sql);
		stat.close();
		return status;
	}
	
	public void closeConnection () throws SQLException {
		conn.close();
	}
}
