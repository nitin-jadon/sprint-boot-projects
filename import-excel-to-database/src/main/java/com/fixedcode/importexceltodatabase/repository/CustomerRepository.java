package com.fixedcode.importexceltodatabase.repository;

import com.fixedcode.importexceltodatabase.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
