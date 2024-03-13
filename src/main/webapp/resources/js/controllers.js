/**
 * 
 */
function addToCart(action){
	document.addForm.action = action;
	document.addForm.submit();
	alert("도서가 장바구니에 추가되었습니다.");
}

function removeFromCart(action) {
    if(confirm("삭제하시겠습니까?")){
		document.removeForm.action = action;
		document.removeForm.submit();
	}
}

function clearCart(){
	if(confirm("장바구니를 전체 삭제하시겠습니까?")){
		document.clearForm.submit();
	}
}

function deleteConfirm(id){
	if(confirm("삭제합니다.") == true ) location.href = "./delete?id=" + id;
	else return;
}

