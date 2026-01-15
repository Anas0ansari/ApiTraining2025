package com.order_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.order_service.entity.PaymentEvent;

@Service
public class KafkaService {
     @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void send(PaymentEvent event) {
        kafkaTemplate.send("decembertopic", event.getPaymentId(), event);
    }
}
