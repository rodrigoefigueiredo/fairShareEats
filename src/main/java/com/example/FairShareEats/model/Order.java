package com.example.FairShareEats.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private List<OrderByPerson> orderByPerson; 
	private BigDecimal shipping;
	private BigDecimal discount;
	private BigDecimal totalPrice;
	private BigDecimal totalItemsPrice;

	public Order(List<OrderByPerson> orderByPerson, BigDecimal shipping, BigDecimal discount) {
		super();
		this.orderByPerson = orderByPerson;
		this.shipping = shipping;
		this.discount = discount;
	}
	
	public Order() {
		super();
		this.orderByPerson = new ArrayList<OrderByPerson>();
		this.shipping = new BigDecimal("0.00");
		this.discount = new BigDecimal("0.00");
		this.totalPrice = new BigDecimal("0.00");
		this.totalItemsPrice = new BigDecimal("0.00");
	}

	public BigDecimal getShipping() {
		return shipping;
	}
	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
		recalculateOrderPrice();
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
		recalculateOrderPrice();
	}
	public List<OrderByPerson> getOrderByPerson() {
		return orderByPerson;
	}
	public void setOrderByPerson(List<OrderByPerson> orderByPerson) {
		this.orderByPerson = orderByPerson;
	}
	public BigDecimal getTotalPrice() {
		recalculateOrderPrice();
		return totalPrice;
	}
	public BigDecimal getTotalItemsPrice() {
		recalculateOrderItemsPrice();
		return this.totalItemsPrice;
	}
	public void addOrderPerson (OrderByPerson orderByPerson) {
		this.orderByPerson.add(orderByPerson);
		recalculateOrderPrice();
	}
	
	public void recalculateOrderItemsPrice() {
		this.totalItemsPrice = new BigDecimal("0.00");
		this.orderByPerson.stream().forEach(orderByPerson -> {
			this.totalItemsPrice = this.totalItemsPrice.add(orderByPerson.getTotalPrice());
		});
	}
	
	public void recalculateOrderPrice() {
		this.totalPrice = new BigDecimal("0.00");
		this.recalculateOrderItemsPrice();
		this.totalPrice = this.totalItemsPrice.add(shipping).subtract(discount);
	}
	
}
