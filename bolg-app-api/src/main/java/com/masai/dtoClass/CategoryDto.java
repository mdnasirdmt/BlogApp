package com.masai.dtoClass;

import javax.validation.constraints.NotBlank;

//import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryDto {

	private Integer categoryId;

	@NotBlank
	private String categoryTitle;

	@NotBlank
	private String categoryDescription;
}
