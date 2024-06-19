package com.eventmanagement.eventmanager.model.wrapper;

public class CategoryWithScore {
    private String categoryKey;
    private Integer score;

    public CategoryWithScore() {
    }

    public CategoryWithScore(String categoryKey, Integer score) {
        this.categoryKey = categoryKey;
        this.score = score;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
