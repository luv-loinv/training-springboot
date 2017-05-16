<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
        <title>MH01 - Đăng nhập</title>
    </head>
    <body>
    	<font color="red">${message}</font>
    	<h3>Đăng nhập hệ thống</h3>
    	<form:form id="loginForm" method="post" action="login" modelAttribute="loginBean">
    		<form:label path="userName">Tên đăng nhập</form:label>
			<form:input id="userName" name="userName" path="" /><br>
			<form:label path="passWord">Mật khẩu</form:label>
			<form:password id="passWord" name="passWord" path="" /><br>
			<div class="btn">
				<input type="submit" value="Đăng nhập" />
			</div>
    	</form:form>
    </body>
</html>