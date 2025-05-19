package com.abhisek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhisek.dto.CategoryDto;
import com.abhisek.dto.CategoryResponse;
import com.abhisek.entity.Category;
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

    @GetMapping("/category")
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
}
