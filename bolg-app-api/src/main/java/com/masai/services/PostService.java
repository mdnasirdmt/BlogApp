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

// get post by category
	List<Post> getPostsByCategory(Integer categoryId);

// get all post by user
	List<Post> getPostsByUser(Integer userId);

// search post 
	List<Post> searchPosts(String keyword);
}
