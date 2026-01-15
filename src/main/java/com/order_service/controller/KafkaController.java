package com.order_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order_service.entity.PaymentEvent;
import com.order_service.service.KafkaService;

@RestController
@RequestMapping("/payments")
public class KafkaController {

    @Autowired
    private KafkaService producer;

    @PostMapping
    public String send(@RequestBody PaymentEvent event) {
        producer.send(event);
        return "Message sent to Kafka successfully";
    }
}