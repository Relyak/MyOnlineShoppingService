package com.myonlineshopping.repository;

import com.myonlineshopping.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
}

