<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
		<style type="text/css">
			table{
				border-collapse: collapse;
			}
			table, th, td{
				border:none;
			}
			.alignCenter {
				text-align: right;
				padding-top : 20px;
			}
		</style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    	<font color="red">${message}</font>
    	<h3>Đăng nhập hệ thống</h3>
    	<form:form id="loginForm" method="post" action="login" modelAttribute="loginBean">
    		<form:label path="userName">Tên đăng nhập</form:label>
			<form:input id="userName" name="userName" path="" /><br>
			<form:label path="passWord">Mật khẩu</form:label>
			<form:password id="passWord" name="passWord" path="" /><br>
			<input type="submit" value="Đăng nhập" />
    	</form:form>
    </body>
</html>