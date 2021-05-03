package com.empresa.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.course.entities.User;
import com.empresa.course.services.UserService;

@RestController
@RequestMapping (value = "/users")
public class UserResource {

	// CRIANDO UMA DEPENDENCIA 
	@Autowired
	private UserService service;
	
    
	//ResponseEntity: tipo especifico pra retonar respostas de requisições web, é um Generic
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		
		List <User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
    
  
	@GetMapping(value = "/{id}")
	public ResponseEntity <User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
