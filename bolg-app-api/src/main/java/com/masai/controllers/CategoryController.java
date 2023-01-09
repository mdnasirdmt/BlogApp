package com.masai.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dtoClass.CategoryDto;
import com.masai.exceptions.ApiResponse;
import com.masai.services.CategoryService;

//import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/") // http://localhost:9090/api/categories/
	public ResponseEntity<CategoryDto> createCat(@Valid @RequestBody CategoryDto categoryDto) {

		CategoryDto categoryDto2 = this.categoryService.createCategory(categoryDto);

		return new ResponseEntity<CategoryDto>(categoryDto2, HttpStatus.CREATED);

	}

	@PutMapping("/{categoryId}") // http://localhost:9090/api/categories/1
	public ResponseEntity<CategoryDto> updateCat(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryId) {

		CategoryDto updatedDto = this.categoryService.updateCategory(categoryDto, categoryId);

		return new ResponseEntity<CategoryDto>(updatedDto, HttpStatus.OK);

	}

	@DeleteMapping("/{catId}") // http://localhost:9090/api/categories/1
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer catId) {

		this.categoryService.deleteCategory(catId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted", true), HttpStatus.OK);
	}

	@GetMapping("/{catId}") // http://localhost:9090/api/categories/1
	public ResponseEntity<CategoryDto> getCat(@PathVariable Integer catId) {

		CategoryDto categoryDto = this.categoryService.getCategory(catId);

		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);

	}

	@GetMapping("/") // http://localhost:9090/api/categories/
	public ResponseEntity<List<CategoryDto>> getAllCat() {

		List<CategoryDto> listDtos = this.categoryService.getAllCategories();

		return new ResponseEntity<List<CategoryDto>>(listDtos, HttpStatus.OK);

	}

}
