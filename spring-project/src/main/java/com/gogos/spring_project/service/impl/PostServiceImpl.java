package com.gogos.spring_project.service.impl;

import com.gogos.spring_project.entities.Category;
import com.gogos.spring_project.entities.Post;
import com.gogos.spring_project.entities.User;
import com.gogos.spring_project.exceptions.ResourceNotFoundException;
import com.gogos.spring_project.payloads.PostDto;
import com.gogos.spring_project.payloads.PostResponse;
import com.gogos.spring_project.repositories.CategoryRepo;
import com.gogos.spring_project.repositories.PostRepo;
import com.gogos.spring_project.repositories.UserRepo;
import com.gogos.spring_project.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = postRepo.save(post);

        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));

        postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {

        Pageable p = PageRequest.of(pageNumber, pageSize);

        Page<Post> pagePosts = postRepo.findAll(p);

        List<Post> allPosts = pagePosts.getContent();

        List<PostDto> postDtos = allPosts
                .stream()
                .map((post)-> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElement(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;

    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));

        return modelMapper.map(post, PostDto.class);

    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

        Category cat = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));

        List<Post> posts = postRepo.findByCategory(cat);

        return posts.stream().map((post)-> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));

        List<Post> posts = postRepo.findByUser(user);

        return posts
                .stream()
                .map((post)-> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return List.of();
    }
}
