<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.1.min.js"></script>

	<script type="text/javascript" src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/webjars/angularjs/1.3.14/angular.min.js"></script>
	<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<link href="${jstlCss}" rel="stylesheet" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>
<body>
	
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/home">Home</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse ">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		
		<c:choose>
		<c:when test="${not empty user}">
		<div class="starter-template">
			<h1>Chi tiết thông tin thẻ bảo hiểm</h1>
			
			<div class="content">
				<a><button class="btn btn-warning">Quay lại</button></a>
				<br>
				<br>
				<div class="col-sm-12">
					<div class="col-sm-6">
						<table class="table table-striped">
							<tr>
								<th class="warning col-sm-4">Mã số thẻ bảo hiểm</th>
								<td>${insurace.getInsuranceNumber()}</td>
							</tr>
							<tr>
								<th class="warning">Họ và Tên</th>
								<td>${user.getUserFullName()}</td>
							</tr>
							<tr>
								<th class="warning">Giới tính</th>
								<td>${user.getUserSexDivision()}</td>
							</tr>
							<tr>
								<th class="warning">Ngày sinh</th>
								<td><fmt:formatDate value="${user.getBirthdate()}" pattern="dd/MM/yyyy" /></td>
							</tr>
							<tr>
								<th class="warning">Tên công ty</th>
								<td>${company.getCompanyName()}</td>
							</tr>
							<tr>
								<th class="warning">Nơi đăng ký KCB</th>
								<td>${insurace.getPlaceOfRegister()}</td>
							</tr>
							<tr>
								<th class="warning">Kỳ hạn thẻ bảo hiểm</th>
								<td>
									<fmt:formatDate value="${insurace.getInsuranceStartDate()}" pattern="dd/MM/yyyy" />
									 đến 
									<fmt:formatDate value="${insurace.getInsuranceEndDate()}" pattern="dd/MM/yyyy" />
								</td>
							</tr>
						</table>
					</div>
				</div>
				
				<div class="form-group">
					<a href="/user/delete/${user.userInternalId}"><button class="btn btn-danger">Xóa</button></a>
					<a><button class="btn btn-primary">Cập nhật</button></a>
				</div>
			</div>
		</div>
		</c:when>
		<c:otherwise>
			<div class="alert alert-warning">
			    <a href="#" class="close" data-dismiss="alert">&times;</a>
			    <strong>Warning!</strong> Bản ghi không tồn tại
			</div>
		</c:otherwise>
		</c:choose>
	</div>
</body>

</html>