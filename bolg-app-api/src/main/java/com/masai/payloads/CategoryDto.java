package com.masai.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryDto {

//	private Integer categoryId;

	@NotEmpty
	private String categoryTitle;

	@NotEmpty
	private String categoryDescription;
}
