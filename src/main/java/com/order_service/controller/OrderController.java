package com.order_service.controller;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order_service.entity.Orders;
import com.order_service.serviceInterface.orderServiceInterface;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private orderServiceInterface service;

    @Value("${server.port}")
    private String port;

    // Create order
    @PostMapping
    public String createOrder(@RequestBody Orders ord) {
        return service.createOrders(ord);
    }

    // Get list of orders
    // @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    // public List<Orders> listOfOrders() {
    // List<Orders> orders = service.getListOfOrders();
    // for (Orders o : orders) {
    // o.setInstance("order-service-instance-" + port);
    // }
    // return orders;
    // }
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public UserListResponse getAllEmployees() {
        List<Orders> orders = service.getListOfOrders();
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>("decembertopic", "key-" + 1, "message-" + i));
        }

        producer.close();
        return new UserListResponse(orders); // 200 OK
    }

    // get order by id
    @GetMapping("/{id}")
    public Orders getById(@PathVariable("id") Long id) {
        Orders order = service.getOrderById(id);
        order.setInstance("order-service-instance-" + port);
        return order;

    }

    // update order status
    @PutMapping("update/{id}")
    public String updateOrders(@PathVariable("id") Long id, @RequestBody Orders order) {
        return service.getStatusUpdated(id, order);
    }

    // get status update by id
    @GetMapping("/status/{id}")
    public Map<String, Object> getStatus(@PathVariable("id") Long id) {
        return service.getOrderStatus(id);
    }

}
