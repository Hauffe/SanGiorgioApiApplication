package br.com.desafio.infrastructure.queue;

import br.com.desafio.domain.PaymentItem;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void send(String queue, PaymentItem item) {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(queue, item);
    }
}
