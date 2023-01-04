package com.masai.dtoClass;

import java.util.Date;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	private Integer postId;

	@NotEmpty
	private String title;

	@NotEmpty
	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDto category;

	private UserDto user;

}