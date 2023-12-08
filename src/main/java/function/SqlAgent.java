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
		return stat.executeQuery(sql);
	}
	
	public int executeUpdate (String sql) throws SQLException {
		stat = conn.createStatement();
		return stat.executeUpdate(sql);
	}
	
}
