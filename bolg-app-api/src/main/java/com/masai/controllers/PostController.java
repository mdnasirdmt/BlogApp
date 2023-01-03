package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.ApiResponse;
import com.masai.payloads.PostDto;
import com.masai.services.PostService;
import com.masai.utils.PostResponse;

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
	@DeleteMapping("/{postId}")
	public ApiResponse deletePosts(@PathVariable Integer postId) {

		this.postService.deletePost(postId);

		return new ApiResponse("post deleted id is " + postId, true);

	}

//	get post by idS
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {

		PostDto postDto = this.postService.getPost(postId);

		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);

	}

//	get all post
	@GetMapping("/")
	public ResponseEntity<List<PostDto>> getAllPosts() {

		List<PostDto> allPost = this.postService.getAllPost();

		return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);

	}

//	update post
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePostById(@PathVariable Integer postId, @RequestBody PostDto postDto) {

		PostDto updatePost = this.postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

//	get page by page number and by page size
	@GetMapping("/posts/")
	public ResponseEntity<List<PostDto>> getPostByPageNumber(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

		List<PostDto> allPost = this.postService.paginationOfPost(pageNumber, pageSize);

		return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);

	}

//	get page of post as  response
	@GetMapping("/page/")
	public ResponseEntity<PostResponse> getAllPostByPageNumer(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

		PostResponse postRes = this.postService.getAllPosts(pageNumber, pageSize);

		return new ResponseEntity<PostResponse>(postRes, HttpStatus.OK);

	}

//	get page by sorted
	@GetMapping("/sortBy/")
	public ResponseEntity<PostResponse> getPageBySorted(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

		PostResponse postResponse = this.postService.getPageBySorted(pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

	}

}
