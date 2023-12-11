<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Page</title>
</head>
<body>
<%
//login checker
User user = (User)session.getAttribute("user");
if (user == null) {
	response.sendRedirect("LoginTimeout.jsp");
}
// admin checker
if (!user.getAdmin()) {
	response.sendRedirect("NotAdmin.jsp");
}
%>
<h1>欢迎来到计算机维修任务管理系统！</h1>
<h2>请选择您的操作：</h2>
<a href="AdminTask.jsp"> <button>管理任务</button> </a>
<a href="AdminUser.jsp"> <button>管理用户</button> </a>
<a href="Main.jsp"> <button>返回</button> </a>

</body>
</html>