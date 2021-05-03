package com.empresa.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.course.entities.User;


// Não coloca a anotation @Repository, pois extends de JpaRepository que já é @Repository

public interface UserRepository extends JpaRepository<User, Long> {

}
