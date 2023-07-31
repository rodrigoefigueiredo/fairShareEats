package com.example.FairShareEats.utils.payment.pagSeguro;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.FairShareEats.model.OrderByPerson;
import com.example.FairShareEats.utils.payment.OrderRequest;

public class OrderRequestPagSeguro implements OrderRequest {
	private Customer customer;
	private List<Item> items;
	private List<QrCode> qr_codes;
	private String reference_id;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<QrCode> getQr_codes() {
		return qr_codes;
	}

	public void setQr_codes(List<QrCode> qr_codes) {
		this.qr_codes = qr_codes;
	}

	public String getReference_id() {
		return reference_id;
	}

	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}

	public void create(OrderByPerson orderByPerson) {
		this.customer = new Customer(PagSeguroProperties.API_PAYMENT_PAGSEGURO_EMAIL,
				PagSeguroProperties.API_PAYMENT_PAGSEGURO_TAX_ID, PagSeguroProperties.API_PAYMENT_PAGSEGURO_CUSTOMER);

		ZonedDateTime currentDateTime = ZonedDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
		String formattedDateTimeNow = currentDateTime.format(formatter);
		ZonedDateTime tomorrowDateTime = currentDateTime.plusDays(1);
		String expiration_date = tomorrowDateTime.format(formatter);
		this.reference_id = formattedDateTimeNow;
		int value = orderByPerson.getPaymentProportionalByPerson().multiply(new BigDecimal("100")).intValue();
		this.items = new ArrayList<>();
		this.items.add(new Item(orderByPerson.getPersonName(), 1, value));
		this.qr_codes = new ArrayList<>();
		QrCode qrCode = new QrCode();
		Amount amount = new Amount();
		amount.setValue(String.valueOf(value));
		qrCode.setAmount(amount);
		qrCode.setExpiration_date(expiration_date);
		this.qr_codes.add(qrCode);
	}
}

class Customer {
	private String email;
	private String tax_id;
	private String name;

	public Customer() {
		super();
	}

	public Customer(String email, String tax_id, String name) {
		super();
		this.email = email;
		this.tax_id = tax_id;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTax_id() {
		return tax_id;
	}

	public void setTax_id(String tax_id) {
		this.tax_id = tax_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

class Item {
	private String name;
	private int quantity;
	private int unit_amount;

	public Item(String name, int quantity, int unit_amount) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.unit_amount = unit_amount;
	}

	public Item() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUnit_amount() {
		return unit_amount;
	}

	public void setUnit_amount(int unit_amount) {
		this.unit_amount = unit_amount;
	}
}

class Amount {
	private String value;

	public Amount() {
		super();
	}

	public Amount(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
