package com.clothingstore.service;

import com.clothingstore.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getCustomers(Integer id, String firstName, String lastName, String address) {

        return customerRepository.getAllCustomer(id, firstName, lastName, address);
    }
}
