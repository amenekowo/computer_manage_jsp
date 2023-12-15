package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import bean.Task;

@WebServlet("/UpdateTask")
public class UpdateTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateTask() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set character encoding
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// get jsp thingys
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect("LoginTimeout.jsp");
			return;
		}

		if (user.getAdmin()) {
			String username, taskname;
			String mode;
			taskname = request.getParameter("taskname");
			username = request.getParameter("username");

			// modes: add del
			mode = request.getParameter("opt");
			if (taskname.length() == 0 || taskname == null || username.length() == 0 || username == null) {
				response.sendRedirect("AdminTask.jsp?empty=1");
				return;
			}

			Task task = null;
			
			try {
				if (mode.equals("add")) {
					task = new Task(taskname, username);
					int status = Task.setTask(task);
					if (status == 0) {
						response.sendRedirect("AdminTask.jsp?success=1");
					} else if (status == 1) {
						response.sendRedirect("AdminTask.jsp?failed=1");
					} else {
						String str = "更新数据库-添加任务-出错！";
						response.sendRedirect("Error.jsp?err=" + str);
					}
				} else if (mode.equals("del")) {
					task = new Task(taskname);
					int status = Task.delTask(task);
					if (status == 0) {
						response.sendRedirect("AdminTask.jsp?success=1");
					} else if (status == 1) {
						response.sendRedirect("AdminTask.jsp?failed=1");
					} else {
						String str = "更新数据库-删除任务-出错！";
						response.sendRedirect("Error.jsp?err=" + str);
					}
				}
			} catch (Exception e) {
				String str = e.toString();
				response.sendRedirect("Error.jsp?err=" + str);
			}
		} else {
			response.sendRedirect("NotAdmin.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
