package com.abhisek.service.impl;

import java.util.Date;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhisek.dto.CategoryDto;
import com.abhisek.dto.CategoryResponse;
import com.abhisek.entity.Category;
import com.abhisek.repository.CategoryRepository;
import com.abhisek.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;
    
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public Boolean saveCategory(CategoryDto categoryDto) {
        try {
        	
        Category category	= mapper.map(categoryDto, Category.class);
        	
            // Category category = new Category();
        		
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            category.setIsActive(categoryDto.getIsActive());
            category.setIsDeleted(false);
            category.setCreatedBy(1);
            category.setCreateOn(new Date());

            categoryRepo.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CategoryDto> getAllCategory() {
    	
    	List<Category>categories = categoryRepo.findAll();
    	List<CategoryDto>categoryDtoList = categories.stream().map(cat->mapper.map(cat,CategoryDto.class)).toList();
    	
        return categoryDtoList;
    }

	@Override
	public List<CategoryResponse> getActiveCategory() {
		
    	List<Category>categories = categoryRepo.findByIsActiveTrue();

    	List<CategoryResponse> categoryList = categories.stream().map(cat->mapper.map(cat, CategoryResponse.class)).toList();
    	
		return categoryList;
	}
}
