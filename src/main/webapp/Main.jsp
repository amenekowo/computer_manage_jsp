<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Application</title>
</head>
<body>
<%
// checked user is authed or not
String authed = (String)session.getAttribute("authed");
if (authed == null || authed == "no") {
	out.print("登录超时，请重新登录！");
	out.print("<br><form action=\"Login.jsp\" method=\"post\"><input type=\"submit\" value=\"返回登录\" /></form>");
}
else {
	out.print("<h1>欢迎来到电脑管理系统！</h1> <h2>请选择您的操作：</h2>");
	out.print("<form action=\"logout\"><input type=\"submit\" value=\"退出登录\" /></form>");
}

%>

</body>
</html>