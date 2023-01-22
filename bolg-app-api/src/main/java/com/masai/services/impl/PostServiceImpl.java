package com.masai.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.masai.entities.Category;
import com.masai.entities.Post;
import com.masai.entities.User;
import com.masai.exceptions.ResourceNotFoundException;
import com.masai.payLoad.PostDto;
import com.masai.repository.CategoryRepo;
import com.masai.repository.PostRepo;
import com.masai.repository.UserRepo;
import com.masai.services.PostService;
import com.masai.utils.PostResponse;

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
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post id", postId));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post updatedPost = this.postRepo.save(post);

		PostDto updatedPostDto = this.mapper.map(updatedPost, PostDto.class);

		return updatedPostDto;
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post id", postId));

		this.postRepo.delete(post);

	}

	@Override
	public PostDto getPost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post id", postId));

		PostDto postDto = this.mapper.map(post, PostDto.class);

		return postDto;
	}

	@Override
	public List<PostDto> getAllPost() {

		List<Post> listPost = this.postRepo.findAll();

		List<PostDto> listPostDto = listPost.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return listPostDto;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category id", categoryId));

		List<Post> listPost = this.postRepo.findByCategory(category);

		List<PostDto> listPostDto = listPost.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return listPostDto;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "user id", userId));

		List<Post> listPost = this.postRepo.findByUser(user);

		List<PostDto> listPostDto = listPost.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return listPostDto;
	}

	@Override
	public List<PostDto> paginationOfPost(Integer pageNumber, Integer pageSize) {

		Pageable p = PageRequest.of(pageNumber, pageSize);

		Page<Post> pagePost = this.postRepo.findAll(p);

		List<Post> content = pagePost.getContent();

		List<PostDto> listPostDto = content.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return listPostDto;
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize) {

		Pageable p = PageRequest.of(pageNumber, pageSize);

		Page<Post> pagePost = this.postRepo.findAll(p);

		List<Post> content = pagePost.getContent();

		List<PostDto> listPostDto = content.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(listPostDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostResponse getPageBySorted(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

//		Sort sort = null;
//
//		if (sortDir.equalsIgnoreCase("des")) {
//			sort = Sort.by(sortBy).descending();
//		} else {
//			sort = Sort.by(sortBy).ascending();
//		}

// using tenary operator sorting
		Sort sort = (sortDir.equalsIgnoreCase("des")) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable page = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = this.postRepo.findAll(page);

		List<Post> posts = pagePost.getContent();

		List<PostDto> psotDto = posts.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(psotDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public List<PostDto> searchPostsByTitle(String keyword) {

		List<Post> listPost = this.postRepo.findByTitleContaining(keyword);

		List<PostDto> listPostDto = listPost.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return listPostDto;
	}

//	@Override
//	public List<PostDto> searchPostsByContent(String key) {
//
//		List<Post> listPost = this.postRepo.searchByContent("%"+key+"%");
//
//		List<PostDto> listPostDto = listPost.stream().map((post) -> this.mapper.map(post, PostDto.class))
//				.collect(Collectors.toList());
//
//		return listPostDto;
//	}

}
