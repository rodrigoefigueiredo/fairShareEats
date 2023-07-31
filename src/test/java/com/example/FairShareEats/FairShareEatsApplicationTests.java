package com.example.FairShareEats;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.example.FairShareEats.api.FairShareEatsApplication;
import com.example.FairShareEats.model.Item;
import com.example.FairShareEats.model.Order;
import com.example.FairShareEats.model.OrderByPerson;
import com.example.FairShareEats.services.OrderService;

@SpringBootTest(classes = FairShareEatsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FairShareEatsApplicationTests {
	@Autowired
	private TestRestTemplate rest;
	@Autowired
	private OrderService orderService;
	
	
	private ResponseEntity<Order> postRequest(Order order) {
		ResponseEntity<Order> response = rest.postForEntity("http://localhost:8080/api/v1/fairShareEats/calculateFairShareEatsByPerson", order, Order.class);
		return response;
	}
	
	@Test
	public void calculateFairShareEatsByPersonTest() {
		Order order = new Order();
		OrderByPerson orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Sanduíche", new BigDecimal("10.99")));
		order.addOrderPerson(orderByPerson);
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Pizza", new BigDecimal("89.90")));
		orderByPerson.addItem(new Item("Refrigerante", new BigDecimal("12.90")));
		order.addOrderPerson(orderByPerson);
		order.setDiscount(new BigDecimal("5.00"));
		order.setShipping(new BigDecimal("7.50"));
		ResponseEntity<Order> response = postRequest(order);
		/*
		 * assertEquals((new BigDecimal("9.66")).doubleValue(),
		 * orderService.caculateProportionalPercentage(order.getOrderByPerson().get(0),
		 * order).doubleValue()); assertEquals((new BigDecimal("90.34")).doubleValue(),
		 * orderService.caculateProportionalPercentage(order.getOrderByPerson().get(1),
		 * order).doubleValue());
		 */
		System.out.println(response);
	}
	
	

}
