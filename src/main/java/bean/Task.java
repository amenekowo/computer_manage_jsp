package bean;

import java.sql.SQLException;
import java.io.PrintWriter;
import java.sql.ResultSet;

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
	
	public static void taskList (User user, PrintWriter out) throws ClassNotFoundException, SQLException{
		String sql;
		sql = "SELECT * FROM task WHERE user = '" + user.getUsername() + "'; ";
		queryOut(sql, out, 1);
		return;
	}
	
	public static boolean taskSearch (Task task, int[] mode, PrintWriter out) throws ClassNotFoundException, SQLException{
		// search mode:
		// mode[0]: 1: self 2:all
		// mode[1]: 1: full 2:particial
		String sql = null;
		
		if (mode[1] == 1) {
			if (mode[0] == 1) {
				sql = "SELECT * FROM task WHERE taskname = '" + task.getTaskname() + "' AND user = '" + task.getUser() + "'; ";
				queryOut(sql, out, 1);
				return true;
			}
			else if (mode[0] == 2) {
				sql = "SELECT * FROM task WHERE taskname = '" + task.getTaskname() + "'; ";
				queryOut(sql, out, 1);
				return true;
			}
		}
		else if (mode[1] == 2) {
			if (mode[0] == 1) {
				sql = "SELECT * FROM task WHERE taskname LIKE '%" + task.getTaskname() + "%' AND user = '" + task.getUser() + "'; ";
				queryOut(sql, out, 0);
				return true;
			}
			else if (mode[0] == 2) {
				sql = "SELECT * FROM task WHERE taskname LIKE '%" + task.getTaskname() + "%'; ";
				queryOut(sql, out, 0);
				return true;
			}
		}
		
		return false;
	}
	
	protected static void queryOut(String sql, PrintWriter out, int type) throws SQLException, ClassNotFoundException {
		SqlAgent sqla = new SqlAgent();
		ResultSet res = sqla.executeQuery(sql);
		// type=0, user
		if (type == 0) {
			// out in form
			out.print("<table border=\"1\">"
					+ "<tr>"
					+ "<td>任务名</td>"
					+ "<td>分配的用户</td>"
					+ "</tr>");
			while (res.next()) {
				out.print("<tr>");
				out.print("<td>");
				out.print(res.getString("taskname"));
				out.print("</td>");
				out.print("<td>");
				out.print(res.getString("user"));
				out.print("</td>");
				out.print("</tr>");
			}
			out.print("</table>");
		}
		// type=0, nouser
		else if (type == 1) {
			// out in form
			out.print("<table border=\"1\">"
					+ "<tr>"
					+ "<td>任务名</td>"
					+ "</tr>");
			while (res.next()) {
				out.print("<tr>");
				out.print("<td>");
				out.print(res.getString("taskname"));
				out.print("</td>");
				out.print("</tr>");
			}
			out.print("</table>");
		}
		// close connection
		res.close();
		sqla.destroy();
	}
	
}
