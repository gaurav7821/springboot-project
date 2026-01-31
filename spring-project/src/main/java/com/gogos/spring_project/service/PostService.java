package com.gogos.spring_project.service;


import com.gogos.spring_project.payloads.PostDto;
import com.gogos.spring_project.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //Create post

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //Update post

    PostDto updatePost(PostDto postDto, Integer postId);

    //Delete post

    void deletePost(Integer postId);

    //Get all post

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);

    //Get single post

    PostDto getPostById(Integer postId);

    //Get all post By category

    List<PostDto> getPostsByCategory(Integer categoryId);

    //Get all post By User

    List<PostDto> getPostsByUser(Integer userId);

    //Search Post

    List<PostDto> searchPosts(String keyword);


}
