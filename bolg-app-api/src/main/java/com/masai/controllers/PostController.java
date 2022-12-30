package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.ApiResponse;
import com.masai.payloads.PostDto;
import com.masai.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

//	save post

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

//	get by user

	@GetMapping("/user/{userId}/post")
	public ResponseEntity<List<PostDto>> getPostByUser(@Valid @PathVariable Integer userId) {

		List<PostDto> listPostDto = this.postService.getPostsByUser(userId);

		return new ResponseEntity<List<PostDto>>(listPostDto, HttpStatus.OK);

	}

//	get by category
	@GetMapping("/category/{catId}/post")
	public ResponseEntity<List<PostDto>> getPostByCategory(@Valid @PathVariable Integer catId) {

		List<PostDto> listPostDto = this.postService.getPostsByCategory(catId);

		return new ResponseEntity<List<PostDto>>(listPostDto, HttpStatus.OK);

	}

//	delete post
	@DeleteMapping("/postId")
	public ResponseEntity<ApiResponse> deletePosts(@PathVariable Integer postId) {

		this.postService.deletePost(postId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted id id ", false), HttpStatus.OK);

	}

}
