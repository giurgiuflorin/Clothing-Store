package com.fasttrackit.service;


import com.fasttrackit.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getOrders(Integer orderId, Integer customerId) {

          return orderRepository.getOrders(orderId, customerId);
    }

    @Transactional
    public void addCustomerToOrder(int customerId) {

        orderRepository.addCustomerToOrder(customerId);
    }


}
