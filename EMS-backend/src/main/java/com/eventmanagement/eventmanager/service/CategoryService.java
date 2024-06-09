package com.eventmanagement.eventmanager.service;

import com.eventmanagement.eventmanager.exception.CategoryNotFoundException;
import com.eventmanagement.eventmanager.model.Category;
import com.eventmanagement.eventmanager.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> addCategories(List<Category> categories) {
        return categoryRepo.saveAll(categories);
    }

    public List<Category> findAllCategories(){
        return categoryRepo.findAll();
    }
    public Category updateCategory(Category category){
        return categoryRepo.save(category);
    }

    public boolean doesCategoryExist(Long id) {
        Category category = categoryRepo.findCategoryById(id);
        return category != null;
    }

    public void deleteCategory(Long id){
        categoryRepo.deleteCategoryById(id);
    }
}
