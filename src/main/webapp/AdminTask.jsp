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

if (user == null) {
	out.print("登录超时，请重新登录！");
	out.print("<br><a href=\"logout\"> <button>返回登录</button> </a>");
}
else {
	if (user.getAdmin()) {
		out.print("<h1>欢迎来到计算机维修任务管理系统！</h1>");
	}
	else {
		out.print("你还不是管理员！");
	}
	out.print("<a href=\"Admin.jsp\"> <button>返回</button> </a>");
	out.print("<a href=\"logout\"> <button>退出登录</button> </a>");
}

%>
</body>
</html>