package com.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.domain.Book;
import com.springmvc.domain.Order;
import com.springmvc.repository.BookRepository;
import com.springmvc.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartService cartService;
	
	//재고 수에 대한 주문 가능여부 퍼리. 재고수보다 많으면 예외처리
	public void confirmOrder(String bookId, long quantity) {
		Book bookById = bookRepository.getBookById(bookId);
		if(bookById.getunitInStock()<quantity) {
			throw new IllegalArgumentException("품절입니다. 사용가능한 재고 수: " + bookById.getunitInStock());
		}
		bookById.setunitInStock(bookById.getunitInStock() - quantity);
	}
	
	//주문 내역에 대해 order저장소 객체에 저장, 현재 장바구니 정보를 삭제->주문내역 id반환
	public Long saveOrder(Order order) {
		Long orderId = orderRepository.saveOrder(order);
		cartService.delete(order.getCart().getCartId());
		return orderId;
	}

}
