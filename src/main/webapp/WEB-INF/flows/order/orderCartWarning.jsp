<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.main.css"/>" rel="stylesheet">
<title>Thank you</title>
</head>
<body>
<div class="container">
	<h2 class="alert alert-danger">주문을 취소하였습니다.</h2>
</div>
<div class="container">
	<p><a href="<c:url value="/books"/>" class="btn btn-primary">&laquo; 도서 목록</a></p>
</div>
</body>
</html>