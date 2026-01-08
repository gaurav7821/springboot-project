package com.gogos.spring_project.repositories;

import com.gogos.spring_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
