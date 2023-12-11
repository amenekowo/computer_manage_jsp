<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error detected!</title>
</head>
<body>
<h1>系统出错！请联系管理员并提供以下信息：</h1> <br>
<%
if (request.getParameter("err") != null) 
	out.print(request.getParameter("err"));
%>
</body>
</html>