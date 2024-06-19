package com.eventmanagement.eventmanager.repo;

import com.eventmanagement.eventmanager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category,Long> {

    @Query("SELECT c FROM Category c WHERE c.id = :id")
    Category findCategoryById(@Param("id") Long id);

    @Query("SELECT c FROM Category c WHERE c.categoryKey = :categoryKey")
    Category findCategoryByKey(@Param("categoryKey") String categoryKey);

    void deleteCategoryById(Long id);
}
