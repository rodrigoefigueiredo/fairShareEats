package com.example.FairShareEats.utils.payment.pagSeguro;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.FairShareEats.model.OrderByPerson;
import com.example.FairShareEats.utils.payment.PaymentMethod;

@Component
public class PagSeguro implements PaymentMethod {

	@Override
	public OrderByPerson createOrderPayment(OrderByPerson orderByPerson) {
		RestTemplate restTemplate = new RestTemplate();
		OrderRequestPagSeguro orderRequestPagSeguro = new OrderRequestPagSeguro();
        orderRequestPagSeguro.create(orderByPerson);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/json");
        headers.set("Authorization", PagSeguroProperties.API_PAYMENT_PAGSEGURO_AUTHORIZATION);
        HttpEntity<OrderRequestPagSeguro> requestEntity = new HttpEntity<>(orderRequestPagSeguro, headers);
        ResponseEntity<OrderResponsePagSeguro> responseEntity = restTemplate.exchange(
                PagSeguroProperties.API_PAYMENT_PAGSEGURO_URI,
                HttpMethod.POST,
                requestEntity,
                OrderResponsePagSeguro.class
            );
        /*
        OrderResponsePagSeguro orderResponsePagSeguro = restTemplate.postForObject(
                PagSeguroProperties.API_PAYMENT_PAGSEGURO_URI,
                requestEntity,
                OrderResponsePagSeguro.class
            );
 */
        OrderResponsePagSeguro orderResponsePagSeguro = responseEntity.getBody();
        
        orderByPerson.setQrCodePayment(orderResponsePagSeguro
        		.getQr_codes()
        		.stream()
        		.findFirst()
        		.get()
        		.getLinkPaymentQrCode()
        		.getHref());
		
		return orderByPerson;
	}
}