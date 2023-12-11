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
// checked user is authed or not
User user = (User)session.getAttribute("user");
if (user == null) {
	out.print("登录超时，请重新登录！");
	out.print("<br><form action=\"Login.jsp\" method=\"post\"><input type=\"submit\" value=\"返回登录\" /></form>");
}
else {
	if (user.getAdmin()) {
		out.print("<h1>欢迎来到计算机维修任务管理系统！</h1> <h2>请选择您的操作：</h2>");
		out.print("<form action=\"AdminMission.jsp\"><input type=\"submit\" value=\"管理任务\" /></form>");
		out.print("<form action=\"AdminUser.jsp\"><input type=\"submit\" value=\"管理用户\" /></form>");
	}
	else {
		out.print("你还不是管理员！");
	}
	out.print("<form action=\"Main.jsp\"><input type=\"submit\" value=\"返回\" /></form>");
	out.print("<form action=\"logout\"><input type=\"submit\" value=\"退出登录\" /></form>");
}


%>

</body>
</html>