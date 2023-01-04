package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
	
	
}
