package com.abhisek.service;

import java.util.List;

import com.abhisek.dto.CategoryDto;
import com.abhisek.dto.CategoryResponse;
import com.abhisek.entity.Category;

public interface CategoryService {

    Boolean saveCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategory();

	List<CategoryResponse> getActiveCategory();

	CategoryDto getCategoryById(Integer id);

	Boolean deleteCategory(Integer id);

}
