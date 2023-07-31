package com.example.FairShareEats.model.DTO;

import java.math.BigDecimal;

import com.example.FairShareEats.model.OrderByPerson;

public class OrderByPersonDTO {

	private String personName;
	private BigDecimal paymentProportional;
	private BigDecimal paymentProportionalInPercentage;
	private String qrCodePayment;
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public BigDecimal getPaymentProportional() {
		return paymentProportional;
	}
	public void setPaymentProportional(BigDecimal paymentProportional) {
		this.paymentProportional = paymentProportional;
	}
	public BigDecimal getPaymentProportionalInPercentage() {
		return paymentProportionalInPercentage;
	}
	public void setPaymentProportionalInPercentage(BigDecimal paymentProportionalInPercentage) {
		this.paymentProportionalInPercentage = paymentProportionalInPercentage;
	}
	public String getQrCodePayment() {
		return qrCodePayment;
	}
	public void setQrCodePayment(String qrCodePayment) {
		this.qrCodePayment = qrCodePayment;
	}
	public static OrderByPersonDTO create(OrderByPerson orderByPerson) {
		OrderByPersonDTO orderByPersonDTO = new OrderByPersonDTO();
		orderByPersonDTO.personName = orderByPerson.getPersonName();
		orderByPersonDTO.paymentProportional = orderByPerson.getPaymentProportionalByPerson();
		orderByPersonDTO.paymentProportionalInPercentage = orderByPerson.getPaymentProportionalByPersonInPercentage();
		orderByPersonDTO.qrCodePayment = orderByPerson.getQrCodePayment();
		return orderByPersonDTO;
	}
}
