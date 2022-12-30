package com.masai.services;

import java.util.List;

import com.masai.entities.Post;
import com.masai.payloads.PostDto;

public interface PostService {

// create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

//update
	Post updatePost(PostDto postDto, Integer postId);

// delete
	String deletePost(Integer postId);

// get post
	Post getPost(Integer postId);

//get all posts
	List<Post> getAllPost();

// get posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);

// get all posts by user
	List<PostDto> getPostsByUser(Integer userId);

// search post 
	List<Post> searchPosts(String keyword);
}
