<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<!-- prefix="fn"은 jstl의 function태그를 jsp에서 사용함을 나타냄-->
	
	
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<title>로그인</title>
</head>
<body>
	<div class="container col-md-4">
		<div class="text-center">
			<h3 class="form-signin-heading">Please login</h3>
		</div>
		
		<c:if test="${not empty error}"> <!-- 인증에 실패했을 때 모뎅 속성 error에 저장된 값이 있다면 -->
			<div class="alert alert-danger">
				User이름과 Password가 올바르지 않습니다. <br />
			</div>
		</c:if>
		
		<form class="form-signin" action="<c:url value="/login"/>" method="post">
			<div class="form-group row">
				<input type="text" name="username" class="form-control" placeholder="User Name" required autofocus>
			</div>
			<div class="form-group row">
				<input type="password" name="password" class="form-control" placeholder="Password" required>
			</div>
			<div class="form-group row">
				<button class="btn btn-lg btn-success btn-block" type="submit">로그인</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			</div>
		</form>
		
	</div>
</body>
</html>