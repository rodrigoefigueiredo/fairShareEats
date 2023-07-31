package com.example.FairShareEats.utils.payment;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.example.FairShareEats.model.Order;
import com.example.FairShareEats.model.OrderByPerson;

@Component
public interface PaymentMethod {
	OrderByPerson createOrderPayment(OrderByPerson orderByPerson) throws IOException;
}
