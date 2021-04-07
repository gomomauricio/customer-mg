package net.paymentchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.paymentchain.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
