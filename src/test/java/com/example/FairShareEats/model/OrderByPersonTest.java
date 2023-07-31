package com.example.FairShareEats.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class OrderByPersonTest {

	@Test
	void addItemTest(){
		OrderByPerson orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Hamburger", new BigDecimal("40.00")));
		assertEquals(1, orderByPerson.getItems().size());
		assertEquals((new BigDecimal("40.00")).doubleValue(), orderByPerson.getItems().get(0).getPrice().doubleValue());
		assertEquals((new BigDecimal("40.00")).doubleValue(), orderByPerson.getTotalPrice().doubleValue());
		
		orderByPerson.addItem(new Item("Sobremesa", new BigDecimal("2.00")));
		assertEquals((new BigDecimal("2.00")).doubleValue(), orderByPerson.getItems().get(1).getPrice().doubleValue());
		assertEquals(2, orderByPerson.getItems().size());
		assertEquals((new BigDecimal("42.00")).doubleValue(), orderByPerson.getTotalPrice().doubleValue());
	}

	@Test
	void recalculatePriceTest(){
		OrderByPerson orderByPerson = new OrderByPerson();
		orderByPerson.addItem(new Item("Hamburger", new BigDecimal("40.00")));
		assertEquals((new BigDecimal("40.00")).doubleValue(), orderByPerson.getTotalPrice().doubleValue());
		
		orderByPerson.addItem(new Item("Sobremesa", new BigDecimal("2.00")));
		assertEquals((new BigDecimal("42.00")).doubleValue(), orderByPerson.getTotalPrice().doubleValue());
	}

}
