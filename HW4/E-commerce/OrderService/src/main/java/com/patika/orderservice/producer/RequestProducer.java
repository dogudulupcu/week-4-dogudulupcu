package com.patika.orderservice.producer;


import com.patika.orderservice.configuration.RabbitMQConfiguration;
import com.patika.orderservice.producer.dto.InvoiceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RequestProducer {

    private final AmqpTemplate amqpTemplate;

    private final RabbitMQConfiguration rabbitMQConfiguration;

    public void sendRequest(InvoiceDTO request){
        log.info("request send "+ request);
        amqpTemplate.convertSendAndReceive(rabbitMQConfiguration.getExchange(),rabbitMQConfiguration.getRoutingkey(),request);
    }

}
