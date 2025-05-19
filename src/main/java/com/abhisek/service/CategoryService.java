package com.abhisek.service;

import java.util.List;

import com.abhisek.entity.Category;

public interface CategoryService {

    Boolean saveCategory(Category category);

    List<Category> getAllCategory();

}
