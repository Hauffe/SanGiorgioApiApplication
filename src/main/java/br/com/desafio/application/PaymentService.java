package br.com.desafio.application;

import br.com.desafio.domain.Payment;
import br.com.desafio.domain.PaymentItem;
import br.com.desafio.domain.PaymentStatus;
import br.com.desafio.domain.PaymentValidator;
import br.com.desafio.infrastructure.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PaymentQueueService queueService;

    public Payment processPayment(Payment payment) {
        //TODO: Validação para quando os pagamentos forem salvos no banco de dados
//        PaymentValidator.validate(payment, paymentRepository);

        payment.getPaymentItems().forEach(item -> {
            item.setPaymentStatus(determineStatus(item));
            queueService.sendToQueue(item);
        });

        return payment;
    }

    private PaymentStatus determineStatus(PaymentItem item) {
        BigDecimal originalValue = new BigDecimal("100.00"); // TODO: Exemplo estático para teste, trocar para a funcionalidade real
        int comparison = item.getPaymentValue().compareTo(originalValue);
        if (comparison < 0) return PaymentStatus.PARTIAL;
        if (comparison == 0) return PaymentStatus.TOTAL;
        return PaymentStatus.EXCESS;
    }
}
