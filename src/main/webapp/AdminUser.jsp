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
//login checker
User user = (User)session.getAttribute("user");
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
<h2>用户管理：</h2>
<form action="usermod" method="post">
用户名：<input type="text" name="username" /><br>
密码：<input type="text" name="password" /><br>
模式：<select name="opt">
  <option value="reset">重置密码</option>
  <option value="add">添加用户</option>
  <option value="setadmin">设为管理员</option>
  <option value="deadmin">取消管理员</option>
</select> <br>
<input type="submit" value="提交"/>
</form>
<%
	if (request.getParameter("empty") != null) {
		if (request.getParameter("empty").equals("1"))
			out.print("用户名或密码不能为空，请重试！");
		else if (request.getParameter("empty").equals("2"))
				out.print("用户名不能为空，请重试！");
			out.print("<br>");
	}
	if (request.getParameter("success") != null) {
		if (request.getParameter("success").equals("1"))
			out.print("更新成功！<br>");
	}
	if (request.getParameter("failed") != null) {
		if (request.getParameter("failed").equals("1"))
			out.print("用户名不存在！<br>");
	}

%>

注：设置管理员不需要填写密码，其余都需要填写密码。<br>
<a href="Admin.jsp"> <button>返回</button> </a>
<a href="logout"> <button>退出登录</button> </a>
</body>
</html>