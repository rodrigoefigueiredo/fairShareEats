package com.example.FairShareEats.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.example.FairShareEats.model.Item;
import com.example.FairShareEats.model.Order;
import com.example.FairShareEats.model.OrderByPerson;

class OrderServiceTest {
	OrderService orderService = new OrderService();

	@Test
	void test() {
		Order order = new Order();
		order.setDiscount(new BigDecimal(0));
		assertEquals(order.getDiscount(), new BigDecimal(0));
	}
	
	@Test
	void calculateProportionalValueTest() {
		Order order = new Order();
		OrderByPerson orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Sanduíche", new BigDecimal("10.00")));
		order.addOrderPerson(orderByPerson);
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Hamburger", new BigDecimal("90.00")));
		order.addOrderPerson(orderByPerson);
		assertEquals((new BigDecimal("10")).doubleValue(), orderService.calculateProportionalValue(order.getOrderByPerson().get(0), order).doubleValue());
		assertEquals((new BigDecimal("90")).doubleValue(), orderService.calculateProportionalValue(order.getOrderByPerson().get(1), order).doubleValue());
		
		order = new Order();
		orderByPerson = new OrderByPerson(); 
		orderByPerson.addItem(new Item("Sanduíche", new BigDecimal("10.00")));
		order.addOrderPerson(orderByPerson);
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Hamburger", new BigDecimal("40.00")));
		order.addOrderPerson(orderByPerson);;
		order.setDiscount(new BigDecimal("10.00"));
		assertEquals((new BigDecimal("8.00")).doubleValue(), orderService.calculateProportionalValue(order.getOrderByPerson().get(0),order).doubleValue());
		assertEquals((new BigDecimal("32.00")).doubleValue(), orderService.calculateProportionalValue(order.getOrderByPerson().get(1),order).doubleValue());
		  
		order = new Order();
		orderByPerson = new OrderByPerson(); 
		orderByPerson.addItem(new Item("Sanduíche", new BigDecimal("10.00")));
		order.addOrderPerson(orderByPerson);
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Pizza", new BigDecimal("70.00")));
		order.addOrderPerson(orderByPerson);
		order.setShipping(new BigDecimal("9.00"));
		assertEquals((new BigDecimal("10.68")).doubleValue(), orderService.calculateProportionalValue(order.getOrderByPerson().get(0), order).doubleValue());
		assertEquals((new BigDecimal("78.32")).doubleValue(), orderService.calculateProportionalValue(order.getOrderByPerson().get(1), order).doubleValue());
		
		order = new Order();
		orderByPerson = new OrderByPerson(); 
		orderByPerson.addItem(new Item("Sanduíche", new BigDecimal("12.99")));
		orderByPerson.addItem(new Item("Sobremesa", new BigDecimal("2.45")));
		order.addOrderPerson(orderByPerson);
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Hamburger", new BigDecimal("13.99")));
		orderByPerson.addItem(new Item("Refrigerante", new BigDecimal("6.99")));
		order.addOrderPerson(orderByPerson);
		order.setShipping(new BigDecimal("9.00"));
		order.setDiscount(new BigDecimal("5.00"));
		assertEquals((new BigDecimal("16.98")).doubleValue(), orderService.calculateProportionalValue(order.getOrderByPerson().get(0), order).doubleValue());
		assertEquals((new BigDecimal("23.44")).doubleValue(), orderService.calculateProportionalValue(order.getOrderByPerson().get(1), order).doubleValue());
		
	}
	
	@Test
	void calculateProportionalPercentageTest() {
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
		assertEquals((new BigDecimal("9.66")).doubleValue(), orderService.caculateProportionalPercentage(order.getOrderByPerson().get(0), order).doubleValue());
		assertEquals((new BigDecimal("90.34")).doubleValue(), orderService.caculateProportionalPercentage(order.getOrderByPerson().get(1), order).doubleValue());
		  
		order = new Order();
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Sanduíche", new BigDecimal("12.00")));
		orderByPerson.addItem(new Item("Suco", new BigDecimal("6.00")));
		order.addOrderPerson(orderByPerson);
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Hamburger", new BigDecimal("22.99")));
		orderByPerson.addItem(new Item("Refrigerante", new BigDecimal("6.90")));
		order.addOrderPerson(orderByPerson);
		order.setDiscount(new BigDecimal("7.00"));
		order.setShipping(new BigDecimal("9.80"));
		assertEquals((new BigDecimal("37.59")).doubleValue(), orderService.caculateProportionalPercentage(order.getOrderByPerson().get(0), order).doubleValue());
		assertEquals((new BigDecimal("62.41")).doubleValue(), orderService.caculateProportionalPercentage(order.getOrderByPerson().get(1), order).doubleValue());
		
		order = new Order();
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Hamburger", new BigDecimal("19.90")));
		order.addOrderPerson(orderByPerson);
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Salgado", new BigDecimal("15.00")));
		order.addOrderPerson(orderByPerson);
		order.setDiscount(new BigDecimal("3.00"));
		order.setShipping(new BigDecimal("8.40"));
		assertEquals((new BigDecimal("57.02")).doubleValue(), orderService.caculateProportionalPercentage(order.getOrderByPerson().get(0), order).doubleValue());
		assertEquals((new BigDecimal("42.98")).doubleValue(), orderService.caculateProportionalPercentage(order.getOrderByPerson().get(1), order).doubleValue());
		
		order = new Order();
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Sanduíche", new BigDecimal("10.00")));
		order.addOrderPerson(orderByPerson);
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Hamburger", new BigDecimal("25.98")));
		orderByPerson.addItem(new Item("Doce", new BigDecimal("5.00")));
		orderByPerson.addItem(new Item("Refrigerante", new BigDecimal("8.00")));
		order.addOrderPerson(orderByPerson);
		order.setDiscount(new BigDecimal("6.00"));
		order.setShipping(new BigDecimal("7.80"));
		assertEquals((new BigDecimal("20.42")).doubleValue(), orderService.caculateProportionalPercentage(order.getOrderByPerson().get(0), order).doubleValue());
		assertEquals((new BigDecimal("79.58")).doubleValue(), orderService.caculateProportionalPercentage(order.getOrderByPerson().get(1), order).doubleValue());
		
		 
	}
	
	@Test
	public void calculatePaymentByPersonTest() throws IOException {
		Order order = new Order();
		OrderByPerson orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Hamburger", new BigDecimal("40.00")));
		orderByPerson.addItem(new Item("Sobremesa", new BigDecimal("2.00")));
		orderByPerson.setPersonName("João");
		order.addOrderPerson(orderByPerson);
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Sanduíche", new BigDecimal("8.00")));
		orderByPerson.setPersonName("Pedro");
		order.addOrderPerson(orderByPerson);
		order.setDiscount(new BigDecimal("20.00"));
		order.setShipping(new BigDecimal("8.00"));
		order = orderService.calculatePaymentByPerson(order);
		
		assertEquals((new BigDecimal("31.92")).doubleValue(), order.getOrderByPerson().get(0).getPaymentProportionalByPerson().doubleValue());
		assertEquals((new BigDecimal("84.00")).doubleValue(), order.getOrderByPerson().get(0).getPaymentProportionalByPersonInPercentage().doubleValue());
		assertEquals((new BigDecimal("6.08")).doubleValue(), order.getOrderByPerson().get(1).getPaymentProportionalByPerson().doubleValue());
		assertEquals((new BigDecimal("16.00")).doubleValue(), order.getOrderByPerson().get(1).getPaymentProportionalByPersonInPercentage().doubleValue());

		order = new Order();
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Hamburger", new BigDecimal("25.98")));
		orderByPerson.addItem(new Item("Sobremesa", new BigDecimal("2.45")));
		orderByPerson.setPersonName("João");
		order.addOrderPerson(orderByPerson);
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Sanduíche", new BigDecimal("8.59")));
		orderByPerson.setPersonName("Pedro");
		order.addOrderPerson(orderByPerson);
		order.setDiscount(new BigDecimal("10.00"));
		order.setShipping(new BigDecimal("9.50"));
		order = orderService.calculatePaymentByPerson(order);
		
		assertEquals((new BigDecimal("28.12")).doubleValue(), order.getOrderByPerson().get(0).getPaymentProportionalByPerson().doubleValue());
		assertEquals((new BigDecimal("76.80")).doubleValue(), order.getOrderByPerson().get(0).getPaymentProportionalByPersonInPercentage().doubleValue());
		assertEquals((new BigDecimal("8.40")).doubleValue(), order.getOrderByPerson().get(1).getPaymentProportionalByPerson().doubleValue());
		assertEquals((new BigDecimal("23.20")).doubleValue(), order.getOrderByPerson().get(1).getPaymentProportionalByPersonInPercentage().doubleValue());
		
		order = new Order();
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Hamburger", new BigDecimal("25.98")));
		orderByPerson.addItem(new Item("Sobremesa", new BigDecimal("2.45")));
		orderByPerson.setPersonName("João");
		order.addOrderPerson(orderByPerson);
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Sanduíche", new BigDecimal("12.99")));
		orderByPerson.setPersonName("Pedro");
		order.addOrderPerson(orderByPerson);
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Hamburger", new BigDecimal("16.98")));
		orderByPerson.addItem(new Item("Refrigerante", new BigDecimal("6.45")));
		orderByPerson.setPersonName("José");
		order.addOrderPerson(orderByPerson);
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Hamburger", new BigDecimal("23.98")));
		orderByPerson.addItem(new Item("Refrigerante", new BigDecimal("7.45")));
		orderByPerson.addItem(new Item("Sobremesa", new BigDecimal("5.00")));
		orderByPerson.setPersonName("Elias");
		order.addOrderPerson(orderByPerson);
		orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Hamburger", new BigDecimal("21.98")));
		orderByPerson.addItem(new Item("Sobremesa", new BigDecimal("2.90")));
		orderByPerson.setPersonName("Paulo");
		order.addOrderPerson(orderByPerson);
		order.setDiscount(new BigDecimal("10.00"));
		order.setShipping(new BigDecimal("9.50"));
		order = orderService.calculatePaymentByPerson(order);
		
		assertEquals((new BigDecimal("28.90")).doubleValue(), order.getOrderByPerson().get(0).getPaymentProportionalByPerson().doubleValue());
		assertEquals((new BigDecimal("22.53")).doubleValue(), order.getOrderByPerson().get(0).getPaymentProportionalByPersonInPercentage().doubleValue());
		assertEquals((new BigDecimal("12.57")).doubleValue(), order.getOrderByPerson().get(1).getPaymentProportionalByPerson().doubleValue());
		assertEquals((new BigDecimal("10.30")).doubleValue(), order.getOrderByPerson().get(1).getPaymentProportionalByPersonInPercentage().doubleValue());
		assertEquals((new BigDecimal("23.88")).doubleValue(), order.getOrderByPerson().get(2).getPaymentProportionalByPerson().doubleValue());
		assertEquals((new BigDecimal("18.57")).doubleValue(), order.getOrderByPerson().get(2).getPaymentProportionalByPersonInPercentage().doubleValue());
		assertEquals((new BigDecimal("36.44")).doubleValue(), order.getOrderByPerson().get(3).getPaymentProportionalByPerson().doubleValue());
		assertEquals((new BigDecimal("28.88")).doubleValue(), order.getOrderByPerson().get(3).getPaymentProportionalByPersonInPercentage().doubleValue());
		assertEquals((new BigDecimal("25.13")).doubleValue(), order.getOrderByPerson().get(4).getPaymentProportionalByPerson().doubleValue());
		assertEquals((new BigDecimal("19.72")).doubleValue(), order.getOrderByPerson().get(4).getPaymentProportionalByPersonInPercentage().doubleValue());
		
	}

}
