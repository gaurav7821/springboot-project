package com.gogos.spring_project.repositories;

import com.gogos.spring_project.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
