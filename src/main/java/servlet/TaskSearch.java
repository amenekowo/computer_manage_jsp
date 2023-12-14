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

import com.mysql.cj.util.SearchMode;

import bean.SqlAgent;
import bean.User;
import bean.Task;

@WebServlet("/TaskSearch")
public class TaskSearch extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public TaskSearch() {
		super();
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

			String taskname = request.getParameter("taskname");
			String mode = request.getParameter("mode");
			String range = request.getParameter("range");
			
			// check name empty
			if (taskname != null)
				if (taskname.length() == 0) {
					response.sendRedirect("TaskSearch.jsp?empty=1");
					return;
				}
			
			// search mode:
			// mode[0]: range 1: self 2:all
			// mode[1]: mode 1: full 2:particial
			
			if (request.getParameter("self") != null && request.getParameter("self").equals("1")) {
				Task.taskList(user, out);
				return;
			}

			String mode_out_string = "搜索模式: ";
			int[] query_mode = new int[2];
			if (mode.equals("full")) {
				query_mode[0] = 1;
				mode_out_string += "精确匹配";
			}
			else if (mode.equals("particial")) {
				query_mode[0] = 2;
				mode_out_string += "模糊匹配";
			}
			mode_out_string += ", ";
			if (range.equals("self")) {
				query_mode[1] = 1;
				mode_out_string += "自己";
			}
			else if (range.equals("all")) {
				query_mode[1] = 2;
				mode_out_string += "全部";
			}
			
			out.print(mode_out_string);
			out.print("<br>");
			out.print("搜索“" + taskname + "”的结果：");
			
			Task task = new Task(taskname, user.getUsername());
			if (!Task.taskSearch(task, query_mode, out)) {
				String str = "查询出错！";
				response.sendRedirect("Error.jsp?err=" + str);
			}
			return;
			
		} catch (Exception e) {
			response.sendRedirect("Error.jsp?err=" + e.toString());
		}
			
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
