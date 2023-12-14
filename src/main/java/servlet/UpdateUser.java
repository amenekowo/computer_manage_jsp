package servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import bean.SqlAgent;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public UpdateUser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set character encoding
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		// get jsp thingys
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if (user == null) {
			response.sendRedirect("LoginTimeout.jsp");
			return;
		}
		
		if (user.getAdmin()) {
			String username, password;
			String mode;
			username = request.getParameter("username");
			password = request.getParameter("password");
			
			// modes: add reset setadmin
			mode = request.getParameter("opt");
			if (mode.equals("setadmin") || mode.equals("deadmin")) {
				// check empty
				if (username.length() == 0 || username == null) {
					response.sendRedirect("AdminUser.jsp?empty=2");
					return;
				}
				else {
					if (mode.equals("setadmin")) {
						try {
							int status = User.setAdmin(username);
							if (status == 0) {
								response.sendRedirect("AdminUser.jsp?success=1");
							} else if (status == 1) {
								response.sendRedirect("AdminUser.jsp?failed=1");
							}
							else {
								String str = "更新数据库-设置管理员-出错！";
								response.sendRedirect("Error.jsp?err=" + str);
							}
						}
						catch (Exception e) {
							String str = e.toString();
							response.sendRedirect("Error.jsp?err=" + str);
						}
					}
					else if (mode.equals("deadmin")) {
						try {
							int status = User.deAdmin(username);
							if (status == 0) {
								response.sendRedirect("AdminUser.jsp?success=1");
							} else if (status == 1) {
								response.sendRedirect("AdminUser.jsp?failed=1");
							} else {
								String str = "更新数据库-取消管理员-出错！";
								response.sendRedirect("Error.jsp?err=" + str);
							}
						}
						catch (Exception e) {
							String str = e.toString();
							response.sendRedirect("Error.jsp?err=" + str);
						}
					}
				}
			}
			else {
				// check empty
				if (username.length() == 0 || password.length() == 0 || username == null || password == null) {
					response.sendRedirect("AdminUser.jsp?empty=1");
					return;
				}
				try {
					if (mode.equals("add")) {
						int status = User.register(username, password);
						// 0 is success
						if (status == 0) {
							response.sendRedirect("AdminUser.jsp?success=1");
						}
						else {
							String str = "更新数据库-添加用户-出错！";
							response.sendRedirect("Error.jsp?err=" + str);
						}
					}
					else if (mode.equals("reset")) {
						int status = User.changePasswordAdmin(username, password);
						// ret stat code, 0 is success
						if (status == 1) {
							response.sendRedirect("AdminUser.jsp?success=1");
						}
						else {
							String str = "更新数据库-重置用户密码-出错！";
							response.sendRedirect("Error.jsp?err=" + str);
						}
					}
				}
				catch (Exception e) {
					String str = e.toString();
					response.sendRedirect("Error.jsp?err=" + str);
				}
			}
		}
		else {
			out.print("<h1>欢迎来到计算机维修任务管理系统！</h1>");
			out.print("你还不是管理员！<br>");
			out.print("<a href=\"Main.jsp\"> <button>返回</button> </a>");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
