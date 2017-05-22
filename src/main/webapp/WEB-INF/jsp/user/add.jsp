<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<body ng-app="app">
	
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
		
		<div class="starter-template" ng-controller="registerUser">
			<h1>Thêm mới thông tin thẻ bảo hiểm</h1>
			
			<div class="alert alert-warning" ng-if="message">
			    <a href="#" class="close" data-dismiss="alert">&times;</a>
			    <strong>Warning!</strong> {{message}}
			</div>
			
			<form action="/user/add" method="post">
				<div class="content">
					<div class="col-sm-12">
						<div class="col-sm-6">
							<table class="table table-striped form-group">
								<tr>
									<th class="warning col-sm-4">Mã số thẻ bảo hiểm</th>
									<td><input type="text" class="form-control" name="insurance_number" id="insurance_number"/></td>
								</tr>
								<tr>
									<th class="warning">Họ và Tên</th>
									<td><input type="text" class="form-control"  name="user_full_name" id="user_full_name"/></td>
								</tr>
								<tr>
									<th class="warning">Giới tính</th>
									<td>
										<label class="radio-inline"><input type="radio"  name="user_sex_division" value="1"> Nam</label>
										<label class="radio-inline"><input type="radio"  name="user_sex_division" value="2"> Nữ</label>
									</td>
								</tr>
								<tr>
									<th class="warning">Ngày sinh</th>
									<td><input type="date" class="form-control"  name="birthdate" id="birthdate"/></td>
								</tr>
								<tr>
									<th class="warning">Tên công ty</th>
									<td>
										<div class="form-group">
											<label class="radio-inline"><input type="radio" ng-model="is_company" data-ng-value="true"> Công ty đã có</label>
											<label class="radio-inline"><input type="radio" ng-model="is_company" data-ng-value="false"> Đăng ký theo công ty mới</label>
										</div>
										<div class="form-group" ng-if="is_company">
											<select class="form-control" id="companyList" ng-model="company_id">
												<option>Luvina</option>
											    <option>Test</option>
											    <option>ABC</option>
											    <option>C</option>
											</select>
										</div>
										<table class="table table-striped form-group">
											<tr>
												<th class="warning">Tên công ty</th>
												<td>
													<input ng-if="!is_company" type="text" class="form-control"  name="user_full_name" id="user_full_name"/>
													<span ng-if="is_company">{{company.name}}</span>
												</td>
											</tr>
											<tr>
												<th class="warning">Địa chỉ</th>
												<td>
													<input ng-if="!is_company" type="text" class="form-control"  name="user_full_name" id="user_full_name"/>
													<span ng-if="is_company">{{company.address}}</span>
												</td>
											</tr>
											<tr>
												<th class="warning">Email</th>
												<td>
													<input ng-if="!is_company" type="text" class="form-control"  name="user_full_name" id="user_full_name"/>
													<span ng-if="is_company">{{company.email}}</span>
												</td>
											</tr>
											<tr>
												<th class="warning">Điện thoại</th>
												<td>
													<input ng-if="!is_company" type="text" class="form-control"  name="user_full_name" id="user_full_name"/>
													<span ng-if="is_company">{{company.phoneNumber}}</span>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="warning">Nơi đăng ký KCB</th>
									<td>
										<input type="text" class="form-control"  name="place_of_register" id="place_of_register"/>
									</td>
								</tr>
								<tr>
									<th class="warning">Ngày bắt đầu thẻ BH</th>
									<td>
										<input type="date" class="form-control"  name="insurance_start_date" id="insurance_start_date"/>
									</td>
								</tr>
								<tr>
									<th class="warning">Ngày kết thúc thẻ BH</th>
									<td>
										<input type="date" class="form-control"  name="insurance_end_date" id="insurance_end_date"/>
									</td>
								</tr>
							</table>
						</div>
					</div>
					
					<div class="btn">
						<a><button class="btn btn-danger" type="reset">Hủy</button></a>
						<a><button class="btn btn-primary" type="submit">Đăng ký</button></a>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
		var app = angular.module('app', []);
		
		app.controller('registerUser', function($scope, $http) {
			//set giá trị mặc định
			$scope.is_company = false;
			var url = "";
			$http.get(url).then(function(response) {
		        $scope.company = response;
		    });
		});
		
		/* 
		app.controller('loadCompany', function($scope, $http) {
			var url = "";
			$http({
				  method: 'GET',
				  url: url
			}).then(
				function successCallback(response) {
				    // this callback will be called asynchronously
				    // when the response is available
				},
				function errorCallback(response) {
				    // called asynchronously if an error occurs
				    // or server returns response with an error status.
			  	}
			);
		}); */
	</script>
</body>

</html>