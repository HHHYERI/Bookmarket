<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<!-- prefix="fn"은 jstl의 function태그를 jsp에서 사용함을 나타냄-->
	
	
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<title>도서 목록</title>
</head>
<body>
	<div class="container">
		<div class="row" align="center">
		
<!-- JSTL의 <for Each>...</for Each>구문을 이용한 반복문
	모든 도서의 목록을 출력 ${bookList}는 BookConrtoller 컨트롤러에서 전달된
	모델 데이터를 var 속성 값인 book으로 다시 정의.
	book을 이용하려 모델 데이터 name, author, publisher 등을 출력
 -->		
			<c:forEach items="${bookList}" var="book">
				<div class="col-md-4">
					<c:choose>
						<c:when test="${book.getBookImage() == null}">
							<img src="<c:url value='/resources/images/${book.fileName}'/>" style="width:60%"/>
						</c:when>
						<c:otherwise>
							<img src="<c:url value='/resources/images/${book.fileName}'/>" style="width:60%"/>
						</c:otherwise> 
					</c:choose> 		
					<h3>${book.name}</h3>
					<p>${book.author }
						<br>${book.publisher } , ${book.releaseDate }
						<p align="left">${fn:substring(book.description, 0, 100)}...
						<p>${book.unitPrice}원
						<p><a href="<c:url value="/books/book?id=${book.bookId}"/>" 
							class="btn btn-secondary" role="button">상세정보 &raquo;</a>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>