package com.payment_service.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.payment_service.dto.Orders;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PaymentService {

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "orderService", fallbackMethod = "orderFallback")
    public List<Orders> getOrders() {

        Orders[] response =
            restTemplate.getForObject(
                "http://order-service/api/orders",
                // "https://gateway/api/v1/security/emplist",
                Orders[].class
            );

        List<Orders> orders = Arrays.asList(response);

        if (!orders.isEmpty()) {
            System.out.println(
                "Selected INSTANCE by LoadBalancer: "
                + orders.get(0).getInstance()
            );
        }

        return orders;
    }

    public List<Orders> orderFallback(Throwable e) {

        Orders dummy = new Orders();
        dummy.setOrderId(0L);
        dummy.setItemName("fallback-item");
        dummy.setPrice(0);
        dummy.setStatus("SERVICE-DOWN");
        dummy.setInstance("fallback-response");

        return List.of(dummy);
    }
}

