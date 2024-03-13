package com.springmvc.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Cart implements Serializable {
	private static final long serialVersionUID = 2155125089108199199L;
			
	private String cartId;
	private Map<String, CartItem> cartItems;
	private int grandTotal;
	
    public Cart() {
        cartItems = new HashMap<String, CartItem>();
        grandTotal = 0;
    }
	
    public Cart(String cartId) {
        this();
        this.cartId = cartId;
    }
	
    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    public int getGrandTotal() {
        return grandTotal;
    }
	
	public void updateGrandTotal() {
		grandTotal = 0;
		for (CartItem item : cartItems.values()) {  //for( A : B )B에서 차례대로 객체를 꺼내서 A에다가 넣겠다
			grandTotal = grandTotal + item.getTotalPrice();
		}
    }
	
	@Override//인터페이스에 정의된 메서드 명세를 재정의
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
        return result;
    }
	
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cart other = (Cart) obj;
        if (cartId == null) {
            if (other.cartId != null)
                return false;
        } else if (!cartId.equals(other.cartId))
            return false;
        return true;
    }
	
	public void addCartItem(CartItem item) {
		String bookId = item.getBook().getBookId(); //현재 등록하기 위한 도서id
		System.out.println("cartItems : " + cartItems.size());
		//도서id가 cartitem객체에 등록되어 있는지 여부 확인
		if(cartItems.containsKey(bookId)) {
			CartItem cartItem = cartItems.get(bookId);//등록된 도서Id정보
			cartItem.setQuantity(cartItem.getQuantity()+item.getQuantity());//도서id의 개수 추가 저장
			cartItems.put(bookId, cartItem); //변경정보저장
			}else {
				cartItems.put(bookId, item); //도서id에대한 도서정보item 저장
		}
			System.out.println("@@@@@@@cartItem:"+item);
			System.out.println("#######bookId:"+bookId);
		updateGrandTotal();
			System.out.println("cartItems : " + cartItems.size());
	}
	
    public void removeCartItem(CartItem item) {
    	String bookId = item.getBook().getBookId();
        cartItems.remove(bookId); // bookId 도서 삭제
        updateGrandTotal();       // 총액 갱신
        
    }
	
	
}
