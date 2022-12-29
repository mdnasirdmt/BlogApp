package com.masai.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entities.Category;
import com.masai.entities.Post;
import com.masai.entities.User;
import com.masai.exceptions.ResourceNotFoundException;
import com.masai.payloads.PostDto;
import com.masai.repository.CategoryRepo;
import com.masai.repository.PostRepo;
import com.masai.repository.UserRepo;
import com.masai.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category id", categoryId));

		Post post = this.mapper.map(postDto, Post.class);

		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);
		

		return this.mapper.map(newPost, PostDto.class);
	}

	@Override
	public Post updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletePost(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPost(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
