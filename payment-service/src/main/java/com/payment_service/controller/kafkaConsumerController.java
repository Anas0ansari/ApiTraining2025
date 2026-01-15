package com.payment_service.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.payment_service.dto.PaymentEvent;


@Component
public class kafkaConsumerController {
    @KafkaListener(
        topics = "decembertopic",
        groupId = "payment-group"
    )
    public void consume(PaymentEvent event) {

        System.out.println("------ Payment Received ------");
        System.out.println("Payment ID : " + event.getPaymentId());
        System.out.println("Amount     : " + event.getAmount());
        System.out.println("Status     : " + event.getStatus());
    }
}
