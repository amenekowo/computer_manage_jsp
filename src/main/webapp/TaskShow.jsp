<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Tasks</title>
</head>
<body>
<%
//login checker
User user = (User)session.getAttribute("user");
if (user == null) {
	response.sendRedirect("LoginTimeout.jsp");
	return;
}
%>

<%
request.getRequestDispatcher("TaskSearch?self=1").forward(request, response);
%>

</body>
</html>