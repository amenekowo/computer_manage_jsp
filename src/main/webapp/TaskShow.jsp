<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.User"%>
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
<jsp:include page="TaskListSelf.jsp" />
<a href="Main.jsp"> <button>返回</button> </a>
</body>
</html>