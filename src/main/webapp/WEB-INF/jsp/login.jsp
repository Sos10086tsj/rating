<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评分考核系统</title>
</head>
<body>
	<div>
		<form name="js_login_form" action="${ctx }/login" method="post">
			<input name="username" placeholder="用户名"/>
			<input type="password" name="password" placeholder="密码"/>
			<button type="submit">登录</button>
		</form>
	</div>
</body>
</html>