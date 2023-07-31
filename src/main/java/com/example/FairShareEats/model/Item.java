package com.example.FairShareEats.model;

import java.math.BigDecimal;

public class Item {
	private String name;
	private BigDecimal price;
	
	public Item(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}
	public Item() {
		this.name = "";
		this.price = new BigDecimal("0.00");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
