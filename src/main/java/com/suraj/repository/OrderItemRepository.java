package com.suraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suraj.model.OrderItem;



public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}