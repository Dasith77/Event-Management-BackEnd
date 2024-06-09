package com.eventmanagement.eventmanager.model.wrapper;

import com.eventmanagement.eventmanager.model.Category;
import com.eventmanagement.eventmanager.model.Event;

import java.util.List;

public class EventWrapper {
    private Event event;
    private List<Category> categoryList;

    public EventWrapper() {
    }

    public EventWrapper(Event event, List<Category> categoryList) {
        this.event = event;
        this.categoryList = categoryList;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
