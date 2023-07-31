package com.example.FairShareEats.utils.payment;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import com.example.FairShareEats.utils.payment.pagSeguro.PagSeguro;

@Component
public class PaymentMethodFactory {
	private Map<String, Supplier<PaymentMethod>> paymentMethods = new HashMap<String, Supplier<PaymentMethod>>();
	public PaymentMethodFactory() {
		this.registerPaymentMethod("PAGSEGURO", PagSeguro::new);
	}
	private void registerPaymentMethod(String name, Supplier<PaymentMethod> supplier) {
        paymentMethods.put(name, supplier);
    }
	
	public PaymentMethod createPaymentMethod(String paymentMethod) {
		Supplier<PaymentMethod> paymentMethodSupplier = paymentMethods.get(paymentMethod);
		if (paymentMethodSupplier == null) {
            throw new IllegalArgumentException("Payment Method not found");
        }
		return paymentMethodSupplier.get();
	}
}
