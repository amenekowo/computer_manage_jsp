<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task Search</title>
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
<h2>请输入您需要查找的任务：</h2>
<form action="checktask">
任务名<input type="text" name="taskname" /> <br>
查找方式
<select name="searchopt">
	<option value="full">精确匹配
	<option value="particial">模糊匹配
</select> <br>
查找范围
<select name="range">
	<option value="self">自己
	<option value="all">全部
</select> <br>
<input type="submit" value="提交" />
</form>
<%
if (request.getParameter("empty") != null) {
	if (request.getParameter("empty").equals("1")) out.print("任务名不能为空！");
}
%>
<br>
<a href="Main.jsp"> <button>返回</button> </a>

<br>
注:任务名为空则为查找全部

</body>
</html>