package com.eventmanagement.eventmanager.model.wrapper;

import com.eventmanagement.eventmanager.model.Category;
import com.eventmanagement.eventmanager.model.Event;

import java.util.List;

public class EventWrapper {
    private Event event;
    private List<CategoryWithScore> categoryWithScoreList;

    public EventWrapper() {
    }

    public EventWrapper(List<CategoryWithScore> categoryWithScoreList, Event event) {
        this.categoryWithScoreList = categoryWithScoreList;
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<CategoryWithScore> getCategoryWithScoreList() {
        return categoryWithScoreList;
    }

    public void setCategoryWithScoreList(List<CategoryWithScore> categoryWithScoreList) {
        this.categoryWithScoreList = categoryWithScoreList;
    }
}
