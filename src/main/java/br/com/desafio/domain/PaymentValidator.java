package br.com.desafio.domain;

import br.com.desafio.domain.exceptions.SellerNotFoundException;
import br.com.desafio.infrastructure.repository.PaymentRepository;

public class PaymentValidator {
    public static void validate(Payment payment, PaymentRepository paymentRepo) {
        if (!paymentRepo.existsById(payment.getClientId())) {
            throw new SellerNotFoundException("Seller not found");
        }
    }
}
