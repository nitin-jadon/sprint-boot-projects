package com.fixedcode.exporttoexcelinjavademo.domain;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Seedata {
    private CustomerRepository customerRepository;
    public Seedata(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }
    private List<Customer> customers = Arrays.asList
            (
            new Customer("hemant", "sharma", "hh@kk", new Address("country", "state", "city", "street")),
            new Customer("sushant", "sharma", "hh@kk", new Address("country", "state", "city", "street")),
            new Customer("vivek", "sharma", "hh@kk", new Address("country", "state", "city", "street")),
            new Customer("prateek", "sharma", "hh@kk", new Address("country", "state", "city", "street"))
            );

   // @PostConstruct
    public void saveCustomers()
    {
       customerRepository.saveAll(customers);
    }
}