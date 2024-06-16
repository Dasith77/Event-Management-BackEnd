package com.eventmanagement.eventmanager.controller;
import com.eventmanagement.eventmanager.model.Category;
import com.eventmanagement.eventmanager.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<List<Category>> addCategories(@RequestBody List<Category> categories) {
        return ResponseEntity.ok(categoryService.addCategories(categories));
    }

    @GetMapping("/all")
    public List<Category> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @PutMapping("/update")
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

}
