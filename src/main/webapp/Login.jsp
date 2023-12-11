<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="bean.SqlAgent"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>System Login</title>
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
    <h2>用户登录：</h2>
    <form action="login" method="post">
      <label>用户名</label>
      <input type="text" name="username" /><br>
      <label>密码</label>
      <input type="password" name="password" /><br>
      <input type="submit" value="登录" />
    </form>
    <%
    // move sqlagent init to login.jsp to avoid everytime reuse of it
    if (session.getAttribute("SqlAgent") == null) {
		
	}
    if (request.getParameter("failed") != null) {
    	if (request.getParameter("failed").equals("1")) out.print("用户名或密码输入错误，请重试！");
    }
    if (request.getParameter("empty") != null) {
    	if (request.getParameter("empty").equals("1")) out.print("用户名或密码不能为空，请重试！");
    }
    if (request.getParameter("regnew") != null) {
    	if (request.getParameter("regnew").equals("1")) out.print("注册成功！请登录");
    }
    
    %>
    <br>
    <br>
    <form action="Register.jsp">
      没有账户？<input type="submit" value="注册" />
    </form>
    
  </body>
</html>