package com.order_service.serviceInterface;

import java.util.List;
import java.util.Map;

import com.order_service.entity.Orders;

public interface orderServiceInterface {
    public String createOrders(Orders od);
    public List<Orders> getListOfOrders();
    public Orders getOrderById(Long id);
    public String getStatusUpdated(Long id,Orders order);
    public Map<String,Object> getOrderStatus(Long id);
}
