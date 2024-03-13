<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<nav class="navbar navbar-expand navbar-dark bg-dark">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/home">Book Market</a>
		</div>
	</div>
	<div>
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="<c:url value="/home"/>"> HOME</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/books"/>"> BOOKS</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/books/add"/>"> ADD BOOK</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/cart"/>"> CART</a></li>		
			
			<li class="nav-item">
				<sec:authorize access="isAuthenticated()">
					<form:form action="${pageContext.request.contextPath}/logout" method="POST">
						<input type="submit" class="btn btn-success" value="Logout" />
					</form:form>
				</sec:authorize>
			</li>			
			
			<li class="nav-item">
				<sec:authorize access="!isAuthenticated()">
					<a class="nav-link" href="<c:url value="/login"/>">LOGIN</a>
				</sec:authorize>
			</li>			
		</ul>
	</div>
</nav>
