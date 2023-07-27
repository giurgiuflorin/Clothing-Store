package com.fasttrackit.service;

import com.fasttrackit.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "call clothing_store.get_customers(:id, :firstName, :lastName, :address)", nativeQuery = true)
    List<Customer> getAllCustomer(@Param("id") Integer id,
                                  @Param("firstName") String firstName,
                                  @Param("lastName") String lastName,
                                  @Param("address") String address);
}
