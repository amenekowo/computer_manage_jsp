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
}
//admin checker
if (!user.getAdmin()) {
	response.sendRedirect("NotAdmin.jsp");
}
%>
<h1>欢迎来到计算机维修任务管理系统！</h1>
</body>
</html>