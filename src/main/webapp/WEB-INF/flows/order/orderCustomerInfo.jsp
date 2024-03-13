<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.main.css"/>" rel="stylesheet">
<title>Customer</title>
</head>
<body>
<div class="container">
	<!-- 고객 정보 폼 페이지에서 입력된 데이터를 바인딩하려면 
	modelAttribute 속성을 사용하여 커맨드 객체 order.customer로 설정 -->
	<form:form modelAttribute="order.customer" class="form-horizontal"> 
	<fieldset>
	<legend>고객 세부 사항</legend>
	
	<div class="form-group row">
		<label class="col-sm-2 control-label">고객 ID</label>
		<div class="col-sm-3">
			<form:input path="customerId" class="form-control" />
		</div>
	</div>
	<div class="form-group row">
		<label class="col-sm-2 control-label">성명</label>
		<div class="col-sm-3">
			<form:input path="name" class="form-control"/>
		</div>
	</div>
	<div class="form-group row">
		<label class="col-sm-2 control-label">전화번호</label>
		<div class="col-sm-3">
			<form:input path="phone" class="form-control"/>
		</div>
	</div>
		<div class="form-group row">
		<label class="col-sm-2 control-label">성명</label>
		<div class="col-sm-3">
			<form:input path="name" class="form-control"/>
		</div>
	</div>
		<div class="form-group row">
		<label class="col-sm-2 control-label">국가명</label>
		<div class="col-sm-3">
			<form:input path="address.country" class="form-control"/>
		</div>
	</div>
		<div class="form-group row">
		<label class="col-sm-2 control-label">우편번호</label>
		<div class="col-sm-3">
			<form:input path="address.zipCode" class="form-control"/>
		</div>
	</div>
	<div class="form-group row">
		<label class="col-sm-2 control-label">주소</label>
		<div class="col-sm-5">
			<form:input path="address.addressName" class="form-control"/>
		</div>
	</div>
	<div class="form-group row">
		<label class="col-sm-2 control-label">세부주소</label>
		<div class="col-sm-3">
			<form:input path="address.detailName" class="form-control"/>
		</div>
	</div>
	<!-- _flowExecutionKey는 웹 플로우에서 플로 순번의 키 값 가짐 -->
	<input type="hidden" name="_flowExcuionKey" value="${flowExcuionKey}"/>
		<div class="form-group row">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" class="btn btn-primary" value="등록" name="_eventId_customerInfo"/>
				<button class="btn btn-default" name="_eventId_cancel">취소</button>
			</div>
		</div>
	</fieldset>	
	</form:form>
</div>
</body>
</html>