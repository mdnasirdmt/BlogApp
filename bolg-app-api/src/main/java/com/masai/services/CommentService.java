package com.masai.services;

import com.masai.payLoad.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);
	
}
