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
// checked user is authed or not
User user = (User)session.getAttribute("user");

if (user == null) {
	out.print("登录超时，请重新登录！");
	out.print("<br><a href=\"logout\"> <button>返回登录</button> </a>");
}
else {
	out.print("<h1>欢迎来到计算机维修任务管理系统！</h1> <h2>请选择您的操作：</h2>");
	out.print("您现在作为" + user.getUsername() + "登录<br>");
	if (user.getAdmin()) {
		out.print("<a href=\"Admin.jsp\"> <button>管理员界面</button> </a>");
	}
	out.print("<a href=\"TaskShow.jsp\"> <button>查看任务</button> </a>");
	out.print("<a href=\"TaskSearch.jsp\"> <button>搜索任务</button> </a>");
	out.print("<a href=\"logout\"> <button>退出登录</button> </a>");
}

%>

</body>
</html>