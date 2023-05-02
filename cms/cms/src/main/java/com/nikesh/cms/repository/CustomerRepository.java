package com.nikesh.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nikesh.cms.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
