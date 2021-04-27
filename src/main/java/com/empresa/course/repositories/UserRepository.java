package com.empresa.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
