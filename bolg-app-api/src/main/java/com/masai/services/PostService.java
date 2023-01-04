package com.masai.services;

import java.util.List;

import com.masai.dtoClass.PostDto;
import com.masai.utils.PostResponse;

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

//	pagination of post
	List<PostDto> paginationOfPost(Integer pageNumber, Integer pageSize);

//	pagination and get all post 
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize);

//	get page of post by sorting
	PostResponse getPageBySorted(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

// search post by title
	List<PostDto> searchPostsByTitle(String keyword);

// search post by content
//	List<PostDto> searchPostsByContent(String key);
}
