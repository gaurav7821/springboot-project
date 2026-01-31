package com.gogos.spring_project.controller;

import com.gogos.spring_project.entities.Post;
import com.gogos.spring_project.payloads.ApiResponse;
import com.gogos.spring_project.payloads.PostDto;
import com.gogos.spring_project.payloads.PostResponse;
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

    //Get all post

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue ="postId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
            ){

        PostResponse postResponse = postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

    }

    //Get post by postId

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){

        PostDto post = postService.getPostById(postId);

        return new ResponseEntity<PostDto>(post, HttpStatus.OK);

    }

    //Delete post

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){

        postService.deletePost(postId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Post is deleted successful", true),HttpStatus.OK);

    }

    //Update post

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable Integer postId){

        PostDto updatePost = postService.updatePost(postDto,postId);

        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }
}
