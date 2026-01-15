package com.order_service.controller;
import jakarta.xml.bind.annotation.*;
import java.util.List;

import com.order_service.entity.Orders;
@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserListResponse {

    @XmlElement(name = "user")
    private List<Orders> users;

    public UserListResponse() {}

    public UserListResponse(List<Orders> users) {
        this.users = users;
    }

    public List<Orders> getUsers() {
        return users;
    }

    public void setUsers(List<Orders> users) {
        this.users = users;
    }
}
