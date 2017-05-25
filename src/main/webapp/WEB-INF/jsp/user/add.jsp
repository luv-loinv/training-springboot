<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<body ng-app="app" ng-controller="registerUser">
	
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
		
		<div class="starter-template ng-scope">
			<h1>Thêm mới thông tin thẻ bảo hiểm</h1>
			
			<div class="alert alert-warning" ng-if="message">
			    <a href="#" class="close" data-dismiss="alert">&times;</a>
			    <strong>Warning!</strong> {{message}}
			</div>
			
			<form:form method="post" action="/user/save" commandName="userForm">
				<div class="content">
					<div class="col-sm-12">
						<div class="col-sm-6">
							<table class="table table-striped form-group">
								<tr>
									<th class="warning col-sm-4">Mã số thẻ bảo hiểm</th>
									<td>
										<input type="text" class="form-control" name="insuranceNumber" id="insuranceNumber"/>
									</td>
								</tr>
								<tr>
									<th class="warning">Họ và Tên</th>
									<td><input type="text" class="form-control"  name="userFullName" id="userFullName"/></td>
								</tr>
								<tr>
									<th class="warning">Giới tính</th>
									<td>
										<label class="radio-inline"><input type="radio" checked="checked" name="userSexDivision" value="1"> Nam</label>
										<label class="radio-inline"><input type="radio"  name="userSexDivision" value="2"> Nữ</label>
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
											<label class="radio-inline"><input type="radio" name="isCompany" ng-model="is_company" data-ng-value="true"> Công ty đã có</label>
											<label class="radio-inline"><input type="radio" name="isCompany" ng-model="is_company" data-ng-value="false"> Đăng ký theo công ty mới</label>
										</div>
										<div class="form-group" ng-show="is_company">
											<select class="form-control" name="companyId" ng-model="company" ng-change="loadCompanyDetail()">
												<option ng-repeat="item in listCompany" value="{{item.id}}" >{{item.name}}</option>
											</select>
										</div>
										<table class="table table-striped form-group" ng-model="companyDetail">
											<tr>
												<th class="warning">Tên công ty</th>
												<td>
													<input ng-if="!is_company" type="text" class="form-control"  name="companyName" id="company_name"/>
													<span ng-if="is_company"  >{{companyDetail.companyName}}</span>
												</td>
											</tr>
											<tr>
												<th class="warning">Địa chỉ</th>
												<td>
													<input ng-if="!is_company" type="text" class="form-control"  name="address" id="address"/>
													<span ng-if="is_company"  >{{companyDetail.address}}</span>
												</td>
											</tr>
											<tr>
												<th class="warning">Email</th>
												<td>
													<input ng-if="!is_company" type="text" class="form-control"  name="email" id="email"/>
													<span ng-if="is_company"  >{{companyDetail.email}}</span>
												</td>
											</tr>
											<tr>
												<th class="warning">Điện thoại</th>
												<td>
													<input ng-if="!is_company" type="text" class="form-control"  name="telephone" id="telephone"/>
													<span ng-if="is_company"  >{{companyDetail.telephone}}</span>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="warning">Nơi đăng ký KCB</th>
									<td>
										<input type="text" class="form-control"  name="placeOfRegister" id="placeOfRegister"/>
									</td>
								</tr>
								<tr>
									<th class="warning">Ngày bắt đầu thẻ BH</th>
									<td>
										<input type="date" class="form-control"  name="insuranceStartDate" id="insuranceStartDate"/>
									</td>
								</tr>
								<tr>
									<th class="warning">Ngày kết thúc thẻ BH</th>
									<td>
										<input type="date" class="form-control"  name="insuranceEndDate" id="insuranceEndDate"/>
									</td>
								</tr>
							</table>
						</div>
					</div>
					
					<div class="form-group">
						<button class="btn btn-danger" type="reset">Hủy</button>
						<button class="btn btn-primary" type="submit">Đăng ký</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	
	<script type="text/javascript">
		var app = angular.module('app', []);
		
		app.controller('registerUser', function($scope, $http) {
			//set giá trị mặc định
			$scope.is_company = true;
			// load company
			var url = "/api/company/getlist";
			$http({
				method	: 'GET',
				url		: url
			}).then(
				function successCallback(response) {
					var listCompany = [];
					response.data.forEach(function(entry) {
						listCompany.push({id : entry[0], name: entry[1]});
					});

					if (response.data != null){
						$scope.company = response.data[0][0];
					}

					$scope.listCompany = listCompany;
					$scope.loadCompanyDetail();
				},function errorCallback(response) {}
			);
			
			//func load company detail
			$scope.loadCompanyDetail = function () {
				var url = "/api/company/getdetail/" + $scope.company;
				$http({
					  method: 'GET',
					  url: url
				}).then(
					function successCallback(response) {
					    $scope.companyDetail = response.data;
					}, function errorCallback(response) {}
				);
			}
		});
		
	</script>
</body>

</html>