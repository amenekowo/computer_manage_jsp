package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	Statement stat = null;
	ResultSet res = null;
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
			// load mysql connector
			Class.forName("com.mysql.cj.jdbc.Driver");
			// mysql params, change these to yours.
			// don't forget to create db and grant permission!
			String sql_url = "jdbc:mysql://localhost:3306/jspdemo";
			String sql_user = "demo";
			String sql_pass = "demo123!@#";
			// try connecting
			conn = DriverManager.getConnection(sql_url, sql_user, sql_pass);
			// get statement
			stat = conn.createStatement();
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
			// get result
			res = stat.executeQuery(sql);
			if (res.next()) {
				// set login success flag
				session.setAttribute("logged", true);
				// forward user to main page
				response.sendRedirect("Main.jsp");
			}
			// if not, we give user a attention
			else {
				response.sendRedirect("Login.jsp?failed=1");
			}
		}
		catch (ClassNotFoundException e) {
			out.print("Error in loading SQL connector! " + e);
		}
		catch (SQLException e) {
			out.print("Error in SQL! " + e);
		}
		catch (Exception e) {
			out.print("System error! " + e);
		}
		finally {
			
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
