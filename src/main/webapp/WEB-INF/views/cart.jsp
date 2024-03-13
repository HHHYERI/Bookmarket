<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	
	
	
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/js/controllers.js"/>"></script>
<title>Cart</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	//http://localhost:8080/BookMarket/order?execution=e11s1
	//alert("${cartId}");
});
function clickOrder(url){
	if( $(".cartList").length == 0 ){
		alert("주문할 데이터가 없습니다.");
		return;
	} else {
		location.href = url;
	}
}
</script>
</head>
<body>
	<div class="container">
		<form:form name="clearForm" method="delete">
			<a href="javascript:clearCart()" class="btn btn-danger pull-left"> 전체 삭제하기 </a>
		</form:form>
		<div>
		    <%-- <a href="<c:url value="/order?cartId=${cartId}"/>" class="btn btn-success float-right" id="btn_order">주문하기</a> --%>
			<a href="javascript:clickOrder('../order?cartId=${cartId}')" class="btn btn-success float-right">주문하기</a>
		</div>
		<div style="padding-top: 50px">
			<table class="table table-hover">
			<tr>
				<th>도서</th>
				<th>가격</th>
				<th>수량</th>
				<th>소계</th>
				<th>비고</th>
			</tr>
	        <form:form name="removeForm" method="put" >
	            <c:forEach items="${cart.cartItems}" var="item">
	            <tr class="cartList">
	                <td>${item.value.book.bookId}-${item.value.book.name}</td>
	                <td>${item.value.book.unitPrice}</td>
	                <td>${item.value.quantity}</td>
	                <td>${item.value.totalPrice}</td>
 	                <td><a href="javascript:removeFromCart('../cart/remove/${item.value.book.bookId}')" class="badge badge-danger">삭제</a></td> 
	            </tr>
	            </c:forEach>
	        </form:form>
			<tr>
				<th></th>
				<th></th>
				<th>총액</th>
				<th>${cart.grandTotal}</th>
				<th></th>
			</tr>
			</table>
			<a href="<c:url value="/books"/>" class="btn btn-secondary"> &laquo; 쇼핑 계속하기</a>
		</div>
	</div>
</body>
</html>