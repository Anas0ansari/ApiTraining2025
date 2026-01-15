package com.order_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.order_service.dao.orderRepo;
import com.order_service.entity.Orders;
import com.order_service.serviceInterface.orderServiceInterface;

@Service
public class orderService implements orderServiceInterface {
    @Autowired
    private orderRepo repo;

    @Override
    public String createOrders(Orders od) {
        repo.save(od);
        return "Order created with order id:" + od.getOrderId();
    }

    @Override
    public List<Orders> getListOfOrders() {
        return repo.findAll();
    }

    @Override
    public Orders getOrderById(Long orderId) {
        Optional<Orders> orderData = repo.findById(orderId);
        if (orderData.isPresent()) {
            return orderData.get();

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "order is not found with this" + orderId);
    }

    @Override
    public String getStatusUpdated(Long id, Orders order) {
        Optional<Orders> op = repo.findById(id);

        if (op.isPresent()) {
            Orders oldBody = op.get();
            oldBody.setStatus(order.getStatus());
            repo.save(oldBody);
            return "Order Status updated";
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Order with this id is not present");
    }

    @Override
    public Map<String, Object> getOrderStatus(Long id) {
        Optional<Orders> op = repo.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (op.isPresent()) {

            response.put("orderId", op.get().getOrderId());
            response.put("status", op.get().getStatus());
            return response;

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order is not created");
    }

}
