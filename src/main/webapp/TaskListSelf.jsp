<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Self Tasks</title>
</head>
<body>
<%
request.getRequestDispatcher("checktask?self=1").forward(request, response);
%>
</body>
</html>