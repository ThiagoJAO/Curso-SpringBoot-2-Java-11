package com.empresa.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.course.entities.OrderItem;


// Não coloca a anotation @Repository, pois extends de JpaRepository que já é @Repository

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
