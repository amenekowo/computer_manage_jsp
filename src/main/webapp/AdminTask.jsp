<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task Manager</title>
</head>
<body>
<%

User user = (User)session.getAttribute("user");
// login checker
if (user == null) {
	response.sendRedirect("LoginTimeout.jsp");
	return;
}
//admin checker
if (!user.getAdmin()) {
	response.sendRedirect("NotAdmin.jsp");
}
%>
<h1>欢迎来到计算机维修任务管理系统！</h1>
<h2>任务管理：</h2>
<form action="taskmod" method="post">
任务名：<input type="text" name="taskname" /><br>
分配的用户名：<input type="text" name="username" /><br>
模式：<select name="opt">
  <option value="add">添加任务</option>
  <option value="del">删除任务</option>
</select> <br>
<input type="submit" value="提交"/>
</form>
	<%
	if (request.getParameter("empty") != null) {
		if (request.getParameter("empty").equals("1"))
			out.print("任务名不能为空，请重试！");
		out.print("<br>");
	}
	if (request.getParameter("success") != null) {
		if (request.getParameter("success").equals("1"))
			out.print("更新成功！<br>");
	}
	if (request.getParameter("failed") != null) {
		if (request.getParameter("failed").equals("1"))
			out.print("任务已存在！<br>");
	}
	%>

	<a href="Admin.jsp"> <button>返回</button> </a>
<a href="logout"> <button>退出登录</button> </a>
</body>
</html>