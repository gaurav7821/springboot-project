package com.gogos.spring_project.service.impl;

import com.gogos.spring_project.entities.Category;
import com.gogos.spring_project.entities.Post;
import com.gogos.spring_project.entities.User;
import com.gogos.spring_project.exceptions.ResourceNotFoundException;
import com.gogos.spring_project.payloads.PostDto;
import com.gogos.spring_project.repositories.CategoryRepo;
import com.gogos.spring_project.repositories.PostRepo;
import com.gogos.spring_project.repositories.UserRepo;
import com.gogos.spring_project.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));

        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost =  postRepo.save(post);

        return modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPost() {
        return List.of();
    }

    @Override
    public PostDto getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getPostsByCategory(Integer categoryId) {
        return List.of();
    }

    @Override
    public List<Post> getPostsByUser(Integer userId) {
        return List.of();
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return List.of();
    }
}
