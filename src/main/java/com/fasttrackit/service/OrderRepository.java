package com.fasttrackit.service;

import com.fasttrackit.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "call clothing_store.get_orders(:orderId, :customerId)", nativeQuery = true)
    List<Order> getOrders(@Param("orderId") Integer orderId,
                          @Param("customerId") Integer customerId);

}
