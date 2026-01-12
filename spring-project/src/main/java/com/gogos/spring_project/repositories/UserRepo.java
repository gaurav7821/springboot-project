package com.gogos.spring_project.repositories;

import com.gogos.spring_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface UserRepo extends JpaRepository<User, Integer> {
//  User findByName(String name);



}
