package bean;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Task {
	String taskname, user;
	public Task () {
		this.taskname = null;
		this.user = null;
	}
	
	public Task (String taskname) {
		this.taskname = taskname;
	}
	
	public Task (String taskname, String user) {
		this.taskname = taskname;
		this.user = user;
	}
	
	public String getTaskname () {
		return this.taskname;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public void setTaskname (String taskname) {
		this.taskname = taskname;
	}
	
	public void setUser (String user) {
		this.user = user;
	}
	
	public static int setTask (Task task) throws ClassNotFoundException, SQLException {
		SqlAgent sqla = new SqlAgent();
		String sql;
		int status, update_count;
		ResultSet res = null;
		// check dup
		sql = "SELECT * FROM task WHERE taskname = '" + task.getTaskname() + "';";
		res = sqla.executeQuery(sql);
		if (res.next()) {
			// if exists
			status = 1;
		}
		else {
			sql = "INSERT INTO task (taskname, user) VALUES ('" + task.getTaskname() + "', '"+ task.getUser() + "'); ";
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
	
	public static int delTask (Task task) throws ClassNotFoundException, SQLException {
		// 0: success 1: no task 2: error in update
		SqlAgent sqla = new SqlAgent();
		String sql;
		int status, update_count;
		ResultSet res = null;
		// check dup
		sql = "SELECT * FROM task WHERE taskname = '" + task.getTaskname() + "';";
		res = sqla.executeQuery(sql);
		if (res.next()) {
			sql = "DELETE FROM task WHERE taskname = '" + task.getTaskname() + "'; ";
			update_count = sqla.executeUpdate(sql);
			if (update_count == 1) {
				status = 0;
			}
			else {
				// multiple items
				status = 2;
			}
		}
		else {
			status = 1;
		}
		sqla.destroy();
		return status;
	}
	
	public static ArrayList<Task> taskList (User user) throws ClassNotFoundException, SQLException{
		String sql;
		ArrayList<Task> tasklist = new ArrayList<Task>();
		SqlAgent sqla = new SqlAgent();
		sql = "SELECT * FROM task WHERE user = '" + user.getUsername() + "'; ";
		ResultSet res = sqla.executeQuery(sql);
		while (res.next()) {
			tasklist.add(new Task(res.getString("taskname"), res.getString("user")));
		}
		sqla.destroy();
		return tasklist;
	}
	
	public static ArrayList<Task> taskSearch (Task task, int[] mode) throws ClassNotFoundException, SQLException{
		// search mode:
		// mode[0]: 1: self 2:all
		// mode[1]: 1: full 2:particial
		String sql = null;
		ArrayList<Task> tasklist = new ArrayList<Task>();
		SqlAgent sqla = new SqlAgent();
		if (mode[1] == 1) {
			if (mode[0] == 1) {
				sql = "SELECT * FROM task WHERE taskname = '" + task.getTaskname() + "' AND user = '" + task.getUser() + "'; ";
			}
			else if (mode[0] == 2) {
				sql = "SELECT * FROM task WHERE taskname = '" + task.getTaskname() + "'; ";
			}
		}
		else if (mode[1] == 2) {
			if (mode[0] == 1) {
				sql = "SELECT * FROM task WHERE taskname LIKE '%" + task.getTaskname() + "%' AND user = '" + task.getUser() + "'; ";
			}
			else if (mode[0] == 2) {
				sql = "SELECT * FROM task WHERE taskname LIKE '%" + task.getTaskname() + "%'; ";
			}
		}
		
		
		if (sql != null) {
			ResultSet res = sqla.executeQuery(sql);
			while (res.next()) {
				tasklist.add(new Task(res.getString("taskname"), res.getString("user")));
			}
			sqla.destroy();
			return tasklist;
		}
		else {
			return null;
		}
	}
	
}
