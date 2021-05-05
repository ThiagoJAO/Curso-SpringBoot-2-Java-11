package com.empresa.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.course.entities.Product;
import com.empresa.course.repositories.ProductRepository;

@Service
public class ProductService {
	
	// CRIANDO UMA DEPENDENCIA
	@Autowired
	private ProductRepository repository;
	
	public List <Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional <Product> obj = repository.findById(id);
		return obj.get();
	}

}
