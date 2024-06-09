package com.eventmanagement.eventmanager.model;

import jakarta.persistence.*;

@Entity
@Table
public class Category {
    @Id
    @SequenceGenerator(
            name="category_sequence",
            sequenceName="category_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy =  GenerationType.IDENTITY,
            generator = "category_sequence"
    )
    private Long id;
    private String categoryKey;
    private String categoryName;

    public Category() {
    }

    public Category(long id, String categoryKey, String categoryName) {
        this.id = id;
        this.categoryKey = categoryKey;
        this.categoryName = categoryName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryKey='" + categoryKey + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
