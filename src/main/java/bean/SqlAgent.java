package bean;

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
	
	public User login (String username, String password) throws ClassNotFoundException, SQLException{
		User user = new User(username, password);
		SqlAgent sqla = new SqlAgent();
		String sql;
		sql = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'; ";
		// get result
		ResultSet res = sqla.executeQuery(sql);
		if (res.next()) {
			user.setAuthed(true);
			res.close();
			sql = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "' AND isadmin = '1'; ";
			res = sqla.executeQuery(sql);
			if (res.next()) {
				user.setAdmin(true);
				// !!! don't forget to close res !!!
				res.close();
			}
		}
		else {
			user = null;
		}
		sqla.destroy();
		return user;
	}
	
	public int register (String username, String password) throws ClassNotFoundException, SQLException{
		SqlAgent sqla = new SqlAgent();
		String sql;
		int status, errcode;
		// check username exists
		sql = "SELECT * FROM user WHERE username = '" + username + "';";
		ResultSet res = sqla.executeQuery(sql);
		// if we got an account
		if (res.next()) {
			errcode = 1;
		}
		else {
			sql = "INSERT INTO user (username, password) VALUES ('" + username + "', '"+ password + "'); ";
			status = sqla.executeUpdate(sql);
			if (status == 1) {
				errcode = 0;
			}
			else {
				errcode = 2;
			}
		}
		sqla.destroy();
		return errcode;
	}
	
	public int setTask (String taskname, String user) throws ClassNotFoundException, SQLException {
		SqlAgent sqla = new SqlAgent();
		String sql;
		int status, errcode;
		// check dup
		sql = "SELECT * FROM task WHERE taskname = '" + taskname + "';";
		ResultSet res = sqla.executeQuery(sql);
		if (res.next()) {
			errcode = 1;
		}
		else {
			sql = "INSERT INTO task (taskname, user) VALUES ('" + taskname + "', '"+ user + "'); ";
			status = sqla.executeUpdate(sql);
			if (status == 1) {
				errcode = 0;
			}
			else {
				errcode = 2;
			}
		}
		sqla.destroy();
		return errcode;
	}
	
	public int setAdmin(String username) throws ClassNotFoundException, SQLException {
		SqlAgent sqla = new SqlAgent();
		String sql;
		sql = "UPDATE user SET isadmin = '1' WHERE username = '" + username + "';";
		int status = sqla.executeUpdate(sql);
		sqla.destroy();
		return status;
	}
	
	public int deAdmin(String username) throws ClassNotFoundException, SQLException {
		SqlAgent sqla = new SqlAgent();
		String sql;
		sql = "UPDATE user SET isadmin = '0' WHERE username = '" + username + "';";
		int status = sqla.executeUpdate(sql);
		sqla.destroy();
		return status;
	}
	
	public int changePassword (String username, String old_password, String new_password) throws ClassNotFoundException, SQLException {
		SqlAgent sqla = new SqlAgent();
		String sql;
		sql = "SELECT FROM user WHERE username = '" + username + "' AND password = '" + old_password + "';";
		ResultSet res = sqla.executeQuery(sql);
		if (res.next()) {
			sql = "UPDATE user SET password = '" + new_password + "' WHERE username = '" + username + "';";
			int status = sqla.executeUpdate(sql);
			sqla.destroy();
			return status;
		}
		else {
			// if we failed to verify password
			return -1;
		}
	}
	public int changePasswordAdmin (String username, String password) throws ClassNotFoundException, SQLException {
		SqlAgent sqla = new SqlAgent();
		String sql;
		sql = "UPDATE user SET password = '" + password + "' WHERE username = '" + username + "';";
		int status = sqla.executeUpdate(sql);
		sqla.destroy();
		return status;
	}
}
