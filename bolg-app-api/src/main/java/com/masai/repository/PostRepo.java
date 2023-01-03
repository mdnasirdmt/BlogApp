package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masai.entities.Category;
import com.masai.entities.Post;
import com.masai.entities.User;
import com.masai.payloads.PostDto;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String keyword);

//	@Query("select p from Post p where p.content like :key")
//	List<Post> searchByContent(@Param("key") String string);
}
