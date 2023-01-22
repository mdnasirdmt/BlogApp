package com.masai.controllers;

import java.io.IOException;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.masai.exceptions.ApiResponse;
import com.masai.payLoad.PostDto;
import com.masai.services.FileService;
import com.masai.services.PostService;
import com.masai.utils.ConstValue;
import com.masai.utils.PostResponse;

//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

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
			@RequestParam(value = "pageNumber", defaultValue = ConstValue.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = ConstValue.PAGE_SIZE, required = false) Integer pageSize) {

		List<PostDto> allPost = this.postService.paginationOfPost(pageNumber, pageSize);

		return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);

	}

//	get page of post as  response
	@GetMapping("/page/")
	public ResponseEntity<PostResponse> getAllPostByPageNumer(
			@RequestParam(value = "pageNumber", defaultValue = ConstValue.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = ConstValue.PAGE_SIZE, required = false) Integer pageSize) {

		PostResponse postRes = this.postService.getAllPosts(pageNumber, pageSize);

		return new ResponseEntity<PostResponse>(postRes, HttpStatus.OK);

	}

//	get page by sorted
	@GetMapping("/sortBy/")
	public ResponseEntity<PostResponse> getPageBySorted(
			@RequestParam(value = "pageNumber", defaultValue = ConstValue.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = ConstValue.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = ConstValue.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = ConstValue.SORT_DIR, required = false) String sortDir) {

		PostResponse postResponse = this.postService.getPageBySorted(pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

	}

//	get post by serching title
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> PostByTitle(@PathVariable String keyword) {

		List<PostDto> searchPosts = this.postService.searchPostsByTitle(keyword);

		return new ResponseEntity<List<PostDto>>(searchPosts, HttpStatus.OK);

	}

//	upload image in post
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@PathVariable Integer postId,
			@RequestParam(value = "image") MultipartFile image) throws IOException {

		PostDto postDto = this.postService.getPost(postId);

		String fileName = this.fileService.uploadImage(path, image);

		postDto.setImageName(fileName);

		PostDto updatedPost = this.postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}

//	method to get image  file //  sereve file// http://localhost:9090/api/post/image/dd22a369-0291-4d35-a6b2-659ef576eba0.jpg
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {

		InputStream resource = this.fileService.getResource(path, imageName);

		response.setContentType(MediaType.IMAGE_JPEG_VALUE);

		StreamUtils.copy(resource, response.getOutputStream());

	}

////	get post by serching id
//	@GetMapping("/search/{id}")
//	public ResponseEntity<List<PostDto>> PostByContent(@PathVariable String key) {
//
//		List<PostDto> searchPosts = this.postService.searchPostsByContent(key);
//
//		return new ResponseEntity<List<PostDto>>(searchPosts, HttpStatus.OK);
//
//	}
}
