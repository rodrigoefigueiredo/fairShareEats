package com.example.FairShareEats.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FairShareEats.model.Order;
import com.example.FairShareEats.utils.payment.PaymentMethod;
import com.example.FairShareEats.utils.payment.PaymentMethodFactory;

@Service
public class OrderPaymentService {
	@Autowired
	private PaymentMethodFactory paymentMehods;
	public Order createPayment(Order order) throws IOException {
		PaymentMethod paymentMethod = paymentMehods.createPaymentMethod("PAGSEGURO");
		order.getOrderByPerson().stream()
			.forEach(orderByPerson -> {
				try {
					paymentMethod.createOrderPayment(orderByPerson);
				} catch (IOException e) {
					e.printStackTrace();
				}
		});
		return order;
	}
}