package com.order_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order_service.entity.Orders;

@Repository
public interface orderRepo extends JpaRepository<Orders,Long> {
    
}
