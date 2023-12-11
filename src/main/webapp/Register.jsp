<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
    <style>
      label
        {
         display: inline-block;
         width: 60px;
         text-align: justify;
         text-align-last: justify;
         margin-right: 10px;
        }
    </style>
</head>
<body>
<h1>欢迎来到计算机维修任务管理系统！</h1>
  <h2>注册用户：</h2>
    <form action="register" method="post">
      <label>用户名</label>
      <input type="text" name="username" /><br>
      <label>密码</label>
      <input type="password" name="password" /><br>
      <input type="submit" value="注册" />
    </form>	
     <%
     // if sqlagent is null, go back to login.jsp to create one
     if (session.getAttribute("SqlAgent") == null) {request.getRequestDispatcher("Login.jsp").forward(request, response);}
     if (request.getParameter("failed") != null) {
     	if (request.getParameter("failed").equals("1")) out.print("系统错误，请联系管理员并提供以下报错信息！ " + request.getParameter("err").toString());
     	if (request.getParameter("failed").equals("2")) out.print("用户名已存在！");
     }
    if (request.getParameter("empty") != null) {
    	if (request.getParameter("empty").equals("1")) out.print("用户名或密码不能为空，请重试！");
    }
    %>
    <br>
    <a href="Login.jsp"> <button>返回登录</button> </a> 
</body>
</html>