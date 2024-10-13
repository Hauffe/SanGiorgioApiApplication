package br.com.desafio.application;

import br.com.desafio.domain.PaymentItem;
import br.com.desafio.infrastructure.queue.RabbitMQProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentQueueService {

    @Autowired
    RabbitMQProducer producer;

    @RabbitListener(queues = "partial.payment.queue")
    public void receivePartialPayment(String message) throws InterruptedException {
        Thread.sleep(10000);
        showItem(message);
    }

    @RabbitListener(queues = "total.payment.queue")
    public void receiveTotalPayment(String message) throws InterruptedException {
        Thread.sleep(10000);
        showItem(message);
    }

    @RabbitListener(queues = "excess.payment.queue")
    public void receiveExcessPayment(String message) throws InterruptedException {
        Thread.sleep(10000);
        showItem(message);
    }


    public void sendToQueue(PaymentItem item) {
        switch (item.getPaymentStatus()) {
            case PARTIAL -> producer.send("partial.payment.queue", item);
            case TOTAL -> producer.send("total.payment.queue", item);
            case EXCESS -> producer.send("excess.payment.queue", item);
        }
    }

    private void showItem(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PaymentItem paymentItem = objectMapper.readValue(message, PaymentItem.class);

            System.out.println("PaymentItem salvo com sucesso: " + paymentItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
