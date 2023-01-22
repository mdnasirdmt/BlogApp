package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.ApiResponse;
import com.masai.payLoad.CommentDto;
import com.masai.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {

		CommentDto createComment = this.commentService.createComment(commentDto, postId);

		return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);

	}

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComments(@PathVariable Integer commentId) {
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully " + commentId, true),
				HttpStatus.OK);

	}

}
