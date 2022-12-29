package com.masai.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;

	@Column(name = "title")
	private String categoryTitle;

	@Column(name = "description")
	private String categoryDescription;
}
