package com.springmvc.service;
import com.springmvc.domain.Cart;

public interface CartService {
	Cart create(Cart cart);
	Cart read(String cartId);
	void update(String cartId, Cart cart);
	void delete(String cartId);
	Cart validateCart(String cartId); //장바구니예외처리
}
