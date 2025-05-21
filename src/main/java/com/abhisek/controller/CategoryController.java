package com.abhisek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhisek.dto.CategoryDto;
import com.abhisek.dto.CategoryResponse;
import com.abhisek.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCategory(@RequestBody CategoryDto categoryDto) {
        Boolean isSaved = categoryService.saveCategory(categoryDto);

        if (isSaved) {
            return new ResponseEntity<>("Category saved successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to save category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategory() {
        List<CategoryDto> allCategory = categoryService.getAllCategory();

        if (CollectionUtils.isEmpty(allCategory)) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(allCategory, HttpStatus.OK);
    }
    
    
    @GetMapping("/active-category")
    public ResponseEntity<?> getActiveCategory() {
        List<CategoryResponse> allCategory = categoryService.getActiveCategory();

        if (CollectionUtils.isEmpty(allCategory)) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(allCategory, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id)
    {
    	CategoryDto categoryDto =categoryService.getCategoryById(id);
    	
    	if(ObjectUtils.isEmpty(categoryDto))
    	{
    		return new ResponseEntity<>("Category not found with Id="+id,HttpStatus.NOT_FOUND);
    	}
    	
		return new ResponseEntity<>(categoryDto,HttpStatus.OK);

    
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id)
    {
    	Boolean deleted =categoryService.deleteCategory(id);
    	
    	if(deleted)
    	{
    		return new ResponseEntity<>("Category deleted successfully",HttpStatus.OK);
    	}
    	
		return new ResponseEntity<>("Category Not deleted",HttpStatus.INTERNAL_SERVER_ERROR);

    
    }
}
