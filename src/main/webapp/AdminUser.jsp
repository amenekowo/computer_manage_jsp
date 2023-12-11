<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Manager</title>
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
		out.print("<h2>用户管理：</h2>");
		out.print("<form action=\"\"usermod\" method=\"post\">");
		out.print("用户名：<input type=\"text\" name=\"username\" /><br>");
		out.print("密码：<input type=\"text\" name=\"password\" /><br>");
		out.print("模式：<select name=\"opt\"> <option value=\"reset\">重置密码</option> <option value=\"add\">添加用户</option> <option value=\"setadmin\">设为管理员</option> </select> <br>");
		out.print("<input type=\"submit\" value=\"提交\"/>");
		out.print("</form>");
	}
	else {
		out.print("你还不是管理员！");
	}
	if (request.getParameter("empty") != null) {
		if (request.getParameter("empty").equals("1"))
			out.print("用户名或密码不能为空，请重试！");
		else if (request.getParameter("empty").equals("2"))
				out.print("用户名不能为空，请重试！");
			out.print("<br>");
	}
	out.print("注：设置管理员不需要填写密码，其余都需要填写密码。");
	out.print("<br>");
	out.print("<a href=\"Admin.jsp\"> <button>返回</button> </a>");
	out.print("<a href=\"logout\"> <button>退出登录</button> </a>");
}

%>
</body>
</html>