<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.User, bean.Task, java.util.ArrayList, java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Tasks</title>
</head>
<body>
<%
//login checker
User user = (User)session.getAttribute("user");
if (user == null) {
	response.sendRedirect("LoginTimeout.jsp");
	return;
}
%>
<h1>欢迎来到计算机维修任务管理系统！</h1>
<h2>您现在的任务有：</h2>
<%

String mode = (String)session.getAttribute("taskSearchMode");
ArrayList<Task> tasks = null;
tasks = (ArrayList<Task>)session.getAttribute("tasks");

if (tasks == null || tasks.isEmpty()) {
	out.print("没有可显示的任务！<br>");
}
else {
	if (mode.equals("self")) {

		out.print("<table border=\"1\">"
				+ "<tr>"
				+ "<td>任务名</td>"
				+ "</tr>");
		for (Task t:tasks) {
			out.print("<tr>");
			out.print("<td>");
			out.print(t.getTaskname());
			out.print("</td>");
			out.print("<tr>");
		}
		out.print("</table>");
	}
	else if (mode.equals("all")) {
		out.print("<table border=\"1\">"
			+ "<tr>"
			+ "<td>任务名</td>"
			+ "<td>分配的用户</td>"
			+ "</tr>");
	for (Task t:tasks) {
		out.print("<tr>");
		out.print("<td>");
		out.print(t.getTaskname());
		out.print("</td>");
		out.print("<td>");
		out.print(t.getUser());
		out.print("</td>");
		out.print("<tr>");
	}
	out.print("</table>");
	}
}
%>
<a href="Main.jsp"> <button>返回</button> </a>
</body>
</html>