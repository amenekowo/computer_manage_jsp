<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error detected!</title>
</head>
<body>
<%
if (request.getParameter("err") != null) 
	out.print(request.getParameter("err"));
%>
</body>
</html>