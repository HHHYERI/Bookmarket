package com.springmvc.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.springmvc.domain.Order;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
	private Map<Long, Order> listOfOrders;
	private long nextOrderId;
	
	public OrderRepositoryImpl() {
		listOfOrders = new HashMap<Long, Order>();
		nextOrderId = 2000;
	}

	@Override //주문내역에 대한 id와 주문 내역등을 저장, id를 반환
	public Long saveOrder(Order order) {
		order.setOrderId(getNextOrderId());
		listOfOrders.put(order.getOrderId(),order);
		return order.getOrderId();
	}

	private synchronized Long getNextOrderId() {
		return nextOrderId++;
	}
	

}
