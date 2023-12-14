package bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private String username, password;
	private boolean authed, isAdmin;
	
	public	User () {
		this.username = null;
		this.password = null;
	}
	
	public	User (String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername () {
		return this.username;
	}
	String getPassword () {
		return this.password;
	}
	public boolean getAuthed () {
		return this.authed;
	}
	public boolean getAdmin () {
		return this.isAdmin;
	}
	
	void setUsername (String username) {
		this.username = username;
	}
	void setPassword (String password) {
		this.password = password;
	}
	void setAuthed (boolean auth) {
		this.authed = auth;
	}
	void setAdmin (boolean admin) {
		this.isAdmin = admin;
	}
	
	// functions
	public static User login (String username, String password) throws ClassNotFoundException, SQLException{
		User user = new User(username, password);
		SqlAgent sqla = new SqlAgent();
		String sql;
		ResultSet res = null;
		sql = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'; ";
		// get result
		res = sqla.executeQuery(sql);
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
	
	public static int register (String username, String password) throws ClassNotFoundException, SQLException{
		SqlAgent sqla = new SqlAgent();
		String sql;
		ResultSet res = null;
		int status, update_count;
		// check username exists
		sql = "SELECT * FROM user WHERE username = '" + username + "';";
		res = sqla.executeQuery(sql);
		// if we got an account
		if (res.next()) {
			status = 1;
		}
		else {
			sql = "INSERT INTO user (username, password) VALUES ('" + username + "', '"+ password + "'); ";
			update_count = sqla.executeUpdate(sql);
			if (update_count == 1) {
				status = 0;
			}
			else {
				status = 2;
			}
		}
		sqla.destroy();
		return status;
	}
	
	public static int setAdmin(String username) throws ClassNotFoundException, SQLException {
		// 0: success 1: no user 2: error in update
		SqlAgent sqla = new SqlAgent();
		String sql;
		int status;
		ResultSet res = null;
		sql = "SELECT * FROM user WHERE username = '" + username + "';";
		res = sqla.executeQuery(sql);
		if (res.next()) {
			sql = "UPDATE user SET isadmin = '1' WHERE username = '" + username + "';";
			int update_count = sqla.executeUpdate(sql);
			sqla.destroy();
			if (update_count == 1) {
				status = 0;
			} else {
				status = 2;
			}
		}
		else {
			status = 1;
		}
		return status;
	}
	
	public static int deAdmin(String username) throws ClassNotFoundException, SQLException {
		// 0: success 1: no user 2: error in update
		SqlAgent sqla = new SqlAgent();
		String sql;
		int status;
		ResultSet res = null;
		sql = "SELECT * FROM user WHERE username = '" + username + "';";
		res = sqla.executeQuery(sql);
		if (res.next()) {
			sql = "UPDATE user SET isadmin = '0' WHERE username = '" + username + "';";
			int update_count = sqla.executeUpdate(sql);
			sqla.destroy();
			if (update_count == 1)
				status = 0;
			else
				status = 2;
		} else {
			status = 1;
		}
		return status;
	}
	
	public static int changePassword (String username, String old_password, String new_password) throws ClassNotFoundException, SQLException {
		SqlAgent sqla = new SqlAgent();
		String sql;
		int status;
		ResultSet res = null;
		sql = "SELECT FROM user WHERE username = '" + username + "' AND password = '" + old_password + "';";
		res = sqla.executeQuery(sql);
		if (res.next()) {
			sql = "UPDATE user SET password = '" + new_password + "' WHERE username = '" + username + "';";
			int update_count = sqla.executeUpdate(sql);
			sqla.destroy();
			if (update_count == 1) 
				status = 0;
			else
				status = 1;
			return status;
		}
		else {
			// if we failed to verify password
			return -1;
		}
	}
	public static int changePasswordAdmin (String username, String password) throws ClassNotFoundException, SQLException {
		SqlAgent sqla = new SqlAgent();
		String sql;
		int status;
		sql = "UPDATE user SET password = '" + password + "' WHERE username = '" + username + "';";
		int update_count = sqla.executeUpdate(sql);
		sqla.destroy();
		if (update_count == 1) {
			status = 0;
		}
		else 
			status = 1;
		return status;
	}
	
}
