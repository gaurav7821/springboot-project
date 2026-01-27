package com.gogos.spring_project.controller;

import com.gogos.spring_project.payloads.PostDto;
import com.gogos.spring_project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/")
public class PostController {

    @Autowired
    private PostService postService;

    //create

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
            ){

        PostDto createPost = postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    //Get post by user

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){

        List<PostDto> posts = postService.getPostsByUser(userId);

        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

    }

    //Get post by category

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){

        List<PostDto> posts = postService.getPostsByCategory(categoryId);

        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

    }
}
