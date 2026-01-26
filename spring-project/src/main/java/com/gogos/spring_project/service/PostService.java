package com.gogos.spring_project.service;

import com.gogos.spring_project.entities.Post;
import com.gogos.spring_project.payloads.PostDto;

import java.util.List;

public interface PostService {

    //Create post

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //Update post

    PostDto updatePost(PostDto postDto, Integer postId);

    //Delete post

    void deletePost(Integer postId);

    //Get all post

    List<Post> getAllPost();

    //Get single post

    PostDto getPostById(Integer postId);

    //Get all post By category

    List<Post> getPostsByCategory(Integer categoryId);

    //Get all post By User

    List<Post> getPostsByUser(Integer userId);

    //Search Post

    List<Post> searchPosts(String keyword);


}
