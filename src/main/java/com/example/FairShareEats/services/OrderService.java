package com.example.FairShareEats.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FairShareEats.model.Order;
import com.example.FairShareEats.model.OrderByPerson;

@Service
public class OrderService {
	@Autowired
	private OrderPaymentService orderPaymentService;
	public Order calculatePaymentByPerson(Order order) throws IOException {
		order.getOrderByPerson().stream().forEach(orderByPerson -> {
			BigDecimal value = this.calculateProportionalValue(orderByPerson, order);
			BigDecimal percentage = this.caculateProportionalPercentage(orderByPerson, order);
			orderByPerson.setPaymentProportionalByPerson(value);
			orderByPerson.setPaymentProportionalByPersonInPercentage(percentage);
		});
		createPaymentOrder(order);
		return order;
	} 
	
	public BigDecimal calculateProportionalValue(OrderByPerson orderByPerson, Order order) {
		BigDecimal value = orderByPerson.getTotalPrice().divide(order.getTotalItemsPrice(), 2, RoundingMode.HALF_EVEN);
		value = value.multiply(order.getTotalPrice()).setScale(2, RoundingMode.HALF_EVEN);
		return value;
	}
	
	public BigDecimal caculateProportionalPercentage(OrderByPerson orderByPerson, Order order) {
		BigDecimal value = orderByPerson.getTotalPrice().divide(order.getTotalItemsPrice(), 4, RoundingMode.HALF_EVEN);
		value = value.multiply(new BigDecimal("100.00")).setScale(2);
		return value;
	}
	
	private void createPaymentOrder(Order order) throws IOException {
		orderPaymentService.createPayment(order);
	}
}
