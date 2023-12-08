package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import function.SqlAgent;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// get jsp thingys
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		// set character encoding
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		try {
			// if we don't get an agent for sql, create it
			if (session.getAttribute("SqlAgent") == null) {
				// mysql params, change these to yours.
				// don't forget to create db and grant permission!
				String sql_url = "jdbc:mysql://localhost:3306/jspdemo";
				String sql_user = "demo";
				String sql_pass = "demo123!@#";
				try {
					SqlAgent sqla = new SqlAgent(sql_url, sql_user, sql_pass);
					session.setAttribute("SqlAgent", sqla);
				}
				catch (ClassNotFoundException e) {
					out.print("Error in loading SQL connector!");
				}
				catch (SQLException e) {
					out.print("Error in loading SQL agent!");
				}
			}
			// get our agent
			SqlAgent sqla = (SqlAgent) session.getAttribute("SqlAgent");
			// now we get params from request
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			// if we get empty in user/pass or some absurd occasion when they are null, go back to login page
			if (username.length() == 0 || password.length() == 0 || username == null || password == null) {
				response.sendRedirect("Login.jsp?empty=1");
			}
			// if not, we query from table "user" to check user&pass
			String sql;
			sql = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'; ";
			// if sqlagent don't connected
			if (!sqla.isConnected()) sqla.openConnection();
			// get result
			ResultSet res = sqla.executeQuery(sql);
			if (res.next()) {
				// set login success flag
				session.setAttribute("authed", "yes");
				// forward user to main page
				response.sendRedirect("Main.jsp");
			}
			// if not, we give user a attention
			else {
				session.setAttribute("authed", "no");
				response.sendRedirect("Login.jsp?failed=1");
			}
			// close res
			res.close();
		}
		catch (SQLException e) {
			out.print("Error in SQL! " + e);
		}
		catch (Exception e) {
			out.print("System error! " + e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
