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

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set character encoding
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		// get jsp thiny
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		
		// now we get params from request
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// if we get empty in user/pass or some absurd occasion when they are null, go back to login page
		if (username.length() == 0 || password.length() == 0 || username == null || password == null) {
			//request.getRequestDispatcher("Register.jsp?empty=1").forward(request, response);
			response.sendRedirect("Register.jsp?empty=1");
			// don't forget return
			return;
		}
		
		// get sql agent
		SqlAgent sqla = (SqlAgent)session.getAttribute("SqlAgent");
		// register sql
		String sql;
		try {
			int status;
			// check username exists
			sql = "SELECT * FROM user WHERE username = '" + username + "';";
			ResultSet res = sqla.executeQuery(sql);
			// if we got an account
			if (res.next()) {
				response.sendRedirect("Register.jsp?failed=2");
			}
			else {
				sql = "INSERT INTO user (username, password) VALUES ('" + username + "', '"+ password + "'); ";
				out.print("<br>" + sql);
				status = sqla.executeUpdate(sql);
				if (status == 1) {
					response.sendRedirect("Login.jsp?regnew=1");
				}
				else {
					response.sendRedirect("Register.jsp?failed=1");
				}
			}
		}
		catch (SQLException e) {
			out.print("系统错误，请联系管理员并提供以下报错信息！\n" + e.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
