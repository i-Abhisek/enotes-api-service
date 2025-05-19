package com.abhisek.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhisek.entity.Category;
import com.abhisek.repository.CategoryRepository;
import com.abhisek.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public Boolean saveCategory(Category category) {
        try {
            category.setIsDeleted(false);
            category.setIsActive(true);
            category.setCreatedBy(1); // You can replace this with dynamic user id
            category.setCreateOn(new Date());
            categoryRepo.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }
}
