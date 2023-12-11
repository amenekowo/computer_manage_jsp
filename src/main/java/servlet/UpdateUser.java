package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;

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
		
		if (user.getAdmin()) {
			String username, password;
			String mode;
			username = request.getParameter("username");
			password = request.getParameter("password");

			// modes: add reset setadmin
			mode = request.getParameter("opt");
			out.print("Wow!");
			if (mode.equals("setadmin")) {
				// check empty
				if (username.length() == 0 || username == null) {
					response.sendRedirect("AdminUser.jsp?empty=2");
					return;
				}
			}
			else {
				// check empty
				if (username.length() == 0 || password.length() == 0 || username == null || password == null) {
					response.sendRedirect("AdminUser.jsp?empty=1");
					return;
				}
				if (mode.equals("add")) {
					
				}
				else if (mode.equals("reset")) {
					
				}
			}
		}
		else {
			out.print("你还不是管理员！<br>");
			out.print("<a href=\"Main.jsp\"> <button>返回</button> </a>");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
