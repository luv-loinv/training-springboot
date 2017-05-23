<!DOCTYPE html>
<html>
<head>
<title>AngularJs ng-change Event Example</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script type="text/javascript">
	var app = angular.module('ngchangeApp', []);
	app.controller('ngchangeCtrl', function($scope) {
		$scope.count = 0;
		$scope.check = function() {
			$scope.count = $scope.dataSelect;
		}

		$scope.getdetails = function() {
			$scope.count = $scope.count + 1;
		}

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
				    console.log(response.data);
				}, function errorCallback(response) {}
			);
		}
	});
	
	
</script>
</head>
<body>
	<div ng-app="ngchangeApp" ng-controller="ngchangeCtrl">
		<h2>AngularJs ng-change Event Example</h2>
		<input type="text" ng-change="getdetails()" ng-model="txtval" /><br />
		<br /> <span style="color: Red">No. of Times Input Changes:
			{{count}}</span> 
			
			<select ng-change="check()" ng-model="dataSelect">
				<option value="1">232423</option>
				<option value="3">3</option>
				<option value="2">2</option>
			</select>


		<div class="form-group">
			<label class="radio-inline"><input type="radio" ng-model="is_company" data-ng-value="true"> Công ty đã có</label>
			<label class="radio-inline"><input type="radio" ng-model="is_company" data-ng-value="false"> Đăng ký theo công ty mới</label>
		</div>
		<div class="form-group" ng-if="is_company">
			<select class="form-control" name="company" id="company" ng-model="company" ng-change="loadCompanyDetail()">
				<option ng-repeat="item in listCompany" value="{{item.id}}" >{{item.name}}</option>
			</select>
		</div>
		<table class="table table-striped form-group" ng-model="companyDetail">
			<tr>
				<th class="warning">Tên công ty</th>
				<td>
					<input ng-if="!is_company" type="text" class="form-control"  name="user_full_name" id="user_full_name"/>
					<span ng-if="is_company"  >{{companyDetail.companyName}}</span>
				</td>
			</tr>
			<tr>
				<th class="warning">Địa chỉ</th>
				<td>
					<input ng-if="!is_company" type="text" class="form-control"  name="user_full_name" id="user_full_name"/>
					<span ng-if="is_company"  >{{companyDetail.address}}</span>
				</td>
			</tr>
			<tr>
				<th class="warning">Email</th>
				<td>
					<input ng-if="!is_company" type="text" class="form-control"  name="user_full_name" id="user_full_name"/>
					<span ng-if="is_company"  >{{companyDetail.email}}</span>
				</td>
			</tr>
			<tr>
				<th class="warning">Điện thoại</th>
				<td>
					<input ng-if="!is_company" type="text" class="form-control"  name="user_full_name" id="user_full_name"/>
					<span ng-if="is_company"  >{{companyDetail.telephone}}</span>
				</td>
			</tr>
		</table>


	</div>
</body>
</html>
