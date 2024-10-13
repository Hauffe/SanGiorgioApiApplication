package br.com.desafio.infrastructure.controller;

import br.com.desafio.application.PaymentService;
import br.com.desafio.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PutMapping
    public ResponseEntity<Payment> setPayment(@RequestBody Payment request) {
        Payment response = paymentService.processPayment(request);
        return ResponseEntity.ok(response);
    }
}
