package com.payment_service.controller;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.ThrottlingQuotaExceededException;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.payment_service.dto.Orders;
import com.payment_service.service.PaymentService;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    private boolean started = false;

    @GetMapping
    public List<Orders> getOrders() {
        // if (!started) {
        //     started = true;

        //     new Thread(() -> {
        //         Properties props = new Properties();
        //         props.put("bootstrap.servers", "localhost:9092");
        //         props.put("group.id", "test-group");
        //         props.put("key.deserializer",
        //                 "org.apache.kafka.common.serialization.StringDeserializer");
        //         props.put("value.deserializer",
        //                 "org.apache.kafka.common.serialization.StringDeserializer");

        //         Consumer<String, String> consumer = new KafkaConsumer<>(props);

        //         consumer.subscribe(
        //                 Collections.singletonList("decembertopic"));

        //         try {
        //             while (true) {
        //                 ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

        //                 records.forEach(record -> {
        //                     System.out.println(
        //                             "Received: " + record.value() +
        //                                     ", Partition: " + record.partition());
        //                 });
        //             }
        //         } finally {
        //             consumer.close();
        //         }
        //     }).start();
        // }

        return paymentService.getOrders();
    }
}
