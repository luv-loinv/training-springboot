<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
        <title>MH02 - List</title>
    </head>
    <body>
		<div class="container">
			<div id="navbar" class="collapse navbar-collapse ">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/logout">Logout</a></li>
				</ul>
			</div>
		</div>
    	<div class="content">
	    	<font color="red">${message}</font>
	    	<h2>Danh sách thẻ bảo hiểm</h2>
	    	<h3>Tên công ty</h3>
			<form:form method="post" modelAttribute="companyBean">
		        <table>
		            <tr>
		                <td><form:select path="">
		                      <form:option value="" label="...." />
		                      <form:options items="${company}" />
		                    </form:select>
		                </td>
		            </tr>
		        </table>
			</form:form>
			
	    	<h3>Thông tin tìm kiếm</h3>
	    	<form:form id="searchForm" method="post" action="search" modelAttribute="searchBean">
	    		<form:label path="userFullName">Tên người sử dụng</form:label>
				<form:input id="userFullName" name="userFullName" path="" /><br>
				<form:label path="insuranceNumber">Mã số thẻ bảo hiểm</form:label>
				<form:password id="insuranceNumber" name="insuranceNumber" path="" /><br>
				<form:label path="placeOfRegister">Nơi đăng ký</form:label>
				<form:password id="placeOfRegister" name="placeOfRegister" path="" /><br>
				<div class="btn">
					<input type="submit" value="Tìm kiếm" />
			 	</div>
	    	</form:form>
	    	<div style="margin-top:20px">
		    	<div class="btn">
		    		<input type="submit" value="Đăng ký" />
				</div>
	    	</div>
			<div>
				<c:url var="firstUrl" value="/pages/1" />
				<c:url var="lastUrl" value="/pages/${deploymentLog.totalPages}" />
				<c:url var="prevUrl" value="/pages/${currentIndex - 1}" />
				<c:url var="nextUrl" value="/pages/${currentIndex + 1}" />
		
				<div class="pagination">
				    <ul>
				        <c:choose>
				            <c:when test="${currentIndex == 1}">
				                <li class="disabled"><a href="#">&lt;&lt;</a></li>
				                <li class="disabled"><a href="#">&lt;</a></li>
				            </c:when>
				            <c:otherwise>
				                <li><a href="${firstUrl}">&lt;&lt;</a></li>
				                <li><a href="${prevUrl}">&lt;</a></li>
				            </c:otherwise>
				        </c:choose>
				        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
				            <c:url var="pageUrl" value="/pages/${i}" />
				            <c:choose>
				                <c:when test="${i == currentIndex}">
				                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
				                </c:when>
				                <c:otherwise>
				                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
				                </c:otherwise>
				            </c:choose>
				        </c:forEach>
				        <c:choose>
				            <c:when test="${currentIndex == deploymentLog.totalPages}">
				                <li class="disabled"><a href="#">&gt;</a></li>
				                <li class="disabled"><a href="#">&gt;&gt;</a></li>
				            </c:when>
				            <c:otherwise>
				                <li><a href="${nextUrl}">&gt;</a></li>
				                <li><a href="${lastUrl}">&gt;&gt;</a></li>
				            </c:otherwise>
				        </c:choose>
				    </ul>
				</div>
			</div>
			<div class="col-sm-6">
		    	<table border="1" width="80%" align="left" style="margin-top:20px">
					 <tr>
						 <th>Tên người sử dụng ▼ ▲</th>
						 <th>Giới tính</th>
						 <th>Ngày sinh</th>
						 <th>Mã số thẻ bảo hiểm</th>
						 <th>Kỳ hạn</th>
						 <th>Nơi đăng ký KCB</th>
					 </tr>
					 <c:if test="${notemptylists}">
						<ul>
							<c:forEach var="listValue" items="${lists}">
							<li>${listValue}</li>
							<tr>
								 <td>$(listValue.getMasp())</td>
								 <td>$(listValue.getMasp())</td>
								 <td>$(listValue.getMasp())</td>
								 <td>$(listValue.getMasp())</td>
								 <td>$(listValue.getMasp())</td>
								 <td>$(listValue.getMasp())</td>
							</tr>
							</c:forEach>
						</ul>
					</c:if>
				 </table>
			</div>
		</div>
		 <script type="text/javascript" src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>