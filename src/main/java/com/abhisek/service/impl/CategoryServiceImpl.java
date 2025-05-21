package com.abhisek.service.impl;

import java.util.Date;

import java.util.List;
import java.util.Optional;

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
    	
    	List<Category>categories = categoryRepo.findByIsDeletedFalse();
    	List<CategoryDto>categoryDtoList = categories.stream().map(cat->mapper.map(cat,CategoryDto.class)).toList();
    	
        return categoryDtoList;
    }

	@Override
	public List<CategoryResponse> getActiveCategory() {
		
    	List<Category>categories = categoryRepo.findByIsActiveTrueAndDeletedFalse();

    	List<CategoryResponse> categoryList = categories.stream().map(cat->mapper.map(cat, CategoryResponse.class)).toList();
    	
		return categoryList;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		
		Optional<Category> findByCatgory=categoryRepo.findByIdAndIsDeletedFalse(id);
		
		if(findByCatgory.isPresent())
		{
			Category category = findByCatgory.get();
			return mapper.map(category,CategoryDto.class);
		}
		return null;
	}

	@Override
	public Boolean deleteCategory(Integer id) {
		Optional<Category> findByCatgory=categoryRepo.findById(id);

		if(findByCatgory.isPresent())
		{
			Category category = findByCatgory.get();
			category.setIsDeleted(true);
			categoryRepo.save(category);
			return true;
		}
		
		return false;
	}
	
	
	
	
}
