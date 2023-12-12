package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SqlAgent;
import bean.User;

@WebServlet("/TaskSearch")
public class TaskSearch extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public TaskSearch() {
		super();
	}

	protected void queryOut(String sql, PrintWriter out, int type) throws SQLException, ClassNotFoundException {
		SqlAgent sqla = new SqlAgent();
		ResultSet res = sqla.executeQuery(sql);
		// type=0, user
		out.print("<h1>欢迎来到计算机维修任务管理系统！</h1>"
				+ "<h2>您现在的任务有：</h2>");
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// set character encoding
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			
			User user = (User)session.getAttribute("user");
			
			if (user == null) {
				response.sendRedirect("LoginTimeout.jsp");
				return;
			}

			String sql = "";
			String taskname = request.getParameter("taskname");
			String searchopt = request.getParameter("searchopt");
			String range = request.getParameter("range");

			if (request.getParameter("self") != null) {
				String self = request.getParameter("self");
				if (self.equals("1")) {
					sql = "SELECT * FROM task WHERE user = '" + user.getUsername() + "'; ";
					queryOut(sql, out, 1);
					out.print("<a href=\"Main.jsp\"> <button>返回</button> </a>");
					return;
				}
			}

			if (taskname != null)
				if (taskname.length() == 0)
					response.sendRedirect("TaskSearch.jsp?empty=1");

			// distinguish type
			if (searchopt.equals("full")) {
				if (range.equals("self")) {
					sql = "SELECT * FROM task WHERE taskname = '" + taskname + "' AND user = '" + user.getUsername()
							+ "'; ";
					queryOut(sql, out, 0);
				} else if (range.equals("all")) {
					sql = "SELECT * FROM task WHERE taskname = '" + taskname + "'; ";
					queryOut(sql, out, 0);
				}
			} else if (searchopt.equals("particial")) {
				if (range.equals("self")) {
					sql = "SELECT * FROM task WHERE taskname LIKE '%" + taskname + "%' AND user = '"
							+ user.getUsername() + "'; ";
					queryOut(sql, out, 1);
				} else if (range.equals("all")) {
					sql = "SELECT * FROM task WHERE taskname LIKE '%" + taskname + "%'; ";
					queryOut(sql, out, 0);
				}
			} else {
				response.sendRedirect("Error.jsp?err=提交了不存在的规则");
			}
			out.print("<br><a href=\"TaskSearch.jsp\"> <button>返回</button> </a>");
		} catch (Exception e) {
			response.sendRedirect("Error.jsp?err=" + e.toString());
		}
			
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
