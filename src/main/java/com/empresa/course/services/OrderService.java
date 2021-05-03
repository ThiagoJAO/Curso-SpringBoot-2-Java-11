package com.empresa.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.course.entities.Order;
import com.empresa.course.entities.User;
import com.empresa.course.repositories.OrderRepository;

@Service
public class OrderService {
	
	// CRIANDO UMA DEPENDENCIA
	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional <Order> obj = repository.findById(id);
		return obj.get();
	}

}
