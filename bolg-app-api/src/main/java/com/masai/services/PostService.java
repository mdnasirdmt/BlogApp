package com.masai.services;

import java.util.List;
import com.masai.payloads.PostDto;

public interface PostService {

// create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

//update
	PostDto updatePost(PostDto postDto, Integer postId);

// delete
	void deletePost(Integer postId);

// get post
	PostDto getPost(Integer postId);

//get all posts
	List<PostDto> getAllPost();

// get posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);

// get all posts by user
	List<PostDto> getPostsByUser(Integer userId);

// search post 
	List<PostDto> searchPosts(String keyword);

//	pagination of post
	List<PostDto> paginationOfPost(Integer pageNumber, Integer pageSize);
}
