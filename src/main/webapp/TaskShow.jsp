<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Tasks</title>
</head>
<body>
<h1>欢迎来到计算机维修任务管理系统！</h1>
<h2>您现在的任务有：</h2>
<%

User user = (User)session.getAttribute("user");

request.getRequestDispatcher("TaskSearch?self=1").forward(request, response);
%>

</body>
</html>