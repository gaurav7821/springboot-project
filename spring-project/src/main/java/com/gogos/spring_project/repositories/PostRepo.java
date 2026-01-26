package com.gogos.spring_project.repositories;

import com.gogos.spring_project.entities.Category;
import com.gogos.spring_project.entities.Post;
import com.gogos.spring_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {


    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
