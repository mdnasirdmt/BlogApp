package com.masai.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entities.Comment;
import com.masai.entities.Post;
import com.masai.exceptions.ResourceNotFoundException;
import com.masai.payLoad.CommentDto;
import com.masai.repository.CommentRepo;
import com.masai.repository.PostRepo;
import com.masai.services.CommentService;
import com.masai.services.PostService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post id", postId));

		Comment comment = this.mapper.map(commentDto, Comment.class);

		comment.setPost(post);
		comment.setUser(post.getUser());

		Comment savedComment = this.commentRepo.save(comment);

		CommentDto updatedCommentDto = this.mapper.map(savedComment, CommentDto.class);

		return updatedCommentDto;
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("comment", "comment id", commentId));

		this.commentRepo.delete(comment);

	}

}
