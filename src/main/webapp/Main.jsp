<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Application</title>
</head>
<body>

<%
//login checker
User user = (User)session.getAttribute("user");
if (user == null) {
	response.sendRedirect("LoginTimeout.jsp");
}
%>

<h1>欢迎来到计算机维修任务管理系统！</h1>
<h2>请选择您的操作：</h2>
您现在作为<%= user.getUsername() %>登录<br>
<%
if (user.getAdmin()) {
	out.print("<a href=\"Admin.jsp\"> <button>管理员界面</button> </a>");
}
%>
<a href="TaskShow.jsp"> <button>查看任务</button> </a>
<a href="TaskSearch.jsp"> <button>搜索任务</button> </a>
<a href="logout"> <button>退出登录</button> </a>
</body>
</html>