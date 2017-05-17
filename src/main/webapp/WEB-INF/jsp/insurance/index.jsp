<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

	<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
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
								<td>${insuraceDetail.insurance_number}</td>
							</tr>
							<tr>
								<th class="warning">Họ và Tên</th>
								<td>${insuraceDetail.user_full_name}</td>
							</tr>
							<tr>
								<th class="warning">Giới tính</th>
								<td>${insuraceDetail.user_sex_division}</td>
							</tr>
							<tr>
								<th class="warning">Ngày sinh</th>
								<td>${insuraceDetail.bithdate}</td>
							</tr>
							<tr>
								<th class="warning">Tên công ty</th>
								<td>${insuraceDetail.company_name}</td>
							</tr>
							<tr>
								<th class="warning">Nơi đăng ký KCB</th>
								<td>${insuraceDetail.place_of_register}</td>
							</tr>
							<tr>
								<th class="warning">Kỳ hạn thẻ bảo hiểm</th>
								<td>
									${insuraceDetail.insurance_start_date} đến 
									${insuraceDetail.insurance_end_date}
								</td>
							</tr>
						</table>
					</div>
				</div>
				
				<div class="btn">
					<a><button class="btn btn-danger">Xóa</button></a>
					<a><button class="btn btn-primary">Cập nhật</button></a>
				</div>
			</div>
		</div>

	</div>

	<script type="text/javascript" src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>