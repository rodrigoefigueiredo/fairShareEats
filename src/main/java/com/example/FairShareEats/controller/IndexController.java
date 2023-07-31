package com.example.FairShareEats.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FairShareEats.model.Order;
import com.example.FairShareEats.model.DTO.OrderByPersonDTO;
import com.example.FairShareEats.services.OrderService;

@RestController
@RequestMapping("/api/v1/fairShareEats/")
public class IndexController {		
	@Autowired
	private OrderService orderService;
		
	@PostMapping("calculateFairShareEatsByPerson")
	public ResponseEntity<List<OrderByPersonDTO>> fairShareEats(@RequestBody Order order) throws IOException {
		System.out.println(order);
		return ResponseEntity.ok(orderService.calculatePaymentByPerson(order)
				.getOrderByPerson().stream()
				.map(OrderByPersonDTO::create).collect(Collectors.toList()));
	}
}
