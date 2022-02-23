package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Customer;

public interface CustomerRep extends JpaRepository<Customer, Integer>{ //CrudRepository
List <Customer> findByCustIdGreaterThan(int custId);
}
