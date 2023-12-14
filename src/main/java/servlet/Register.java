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

import bean.User;

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
		
		try {
			int stat = User.register(username, password);
			if (stat == 0) {
				response.sendRedirect("Login.jsp?regnew=1");
			}
			else if (stat == 1) {
				response.sendRedirect("Register.jsp?failed=1");
			}
		}
		catch (Exception e) {
			response.sendRedirect("Error.jsp?err=" + e.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
