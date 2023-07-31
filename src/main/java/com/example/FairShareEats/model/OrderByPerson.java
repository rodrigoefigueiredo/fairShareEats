package com.example.FairShareEats.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderByPerson {
	private String personName;
	private List<Item> items = new ArrayList<Item>();
	private BigDecimal paymentProportionalByPerson;
	private BigDecimal paymentProportionalByPersonInPercentage;
	private BigDecimal totalPrice;
	private String qrCodePayment;
	
	public OrderByPerson() {
		super();
		this.items = new ArrayList<Item>();
		this.personName = "";
		this.paymentProportionalByPerson = new BigDecimal("0.00");
		this.paymentProportionalByPersonInPercentage = new BigDecimal("0.00");
		this.totalPrice = new BigDecimal("0.00");
	}
	public OrderByPerson(String personName, List<Item> items) {
		super();
		this.personName = personName;
		this.items = items;
		this.paymentProportionalByPerson = new BigDecimal("0.00");
		this.paymentProportionalByPersonInPercentage = new BigDecimal("0.00");
		this.totalPrice = new BigDecimal("0.00");
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public BigDecimal getPaymentProportionalByPerson() {
		return paymentProportionalByPerson;
	}
	public void setPaymentProportionalByPerson(BigDecimal paymentProportionalByPerson) {
		this.paymentProportionalByPerson = paymentProportionalByPerson;
	}
	public BigDecimal getPaymentProportionalByPersonInPercentage() {
		return paymentProportionalByPersonInPercentage;
	}
	public void setPaymentProportionalByPersonInPercentage(BigDecimal paymentProportionalByPersonInPercentage) {
		this.paymentProportionalByPersonInPercentage = paymentProportionalByPersonInPercentage;
	}
	public String getQrCodePayment() {
		return qrCodePayment;
	}
	public void setQrCodePayment(String qrCodePayment) {
		this.qrCodePayment = qrCodePayment;
	}
	public BigDecimal getTotalPrice() {
		this.recalculatePrice();
		return totalPrice;
	}
	
	public void addItem(Item item) {
		this.items.add(item);
		this.recalculatePrice();
	}
	
	public void addItems(List<Item> item) {
		this.items.addAll(items);
		this.recalculatePrice();
	}
	
	public void recalculatePrice() {
		this.totalPrice = new BigDecimal("0.00");
		this.items.stream().forEach(i -> {
	        this.totalPrice = this.totalPrice.add(i.getPrice());
	    });
	}

}
