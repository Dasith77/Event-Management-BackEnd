package com.eventmanagement.eventmanager.service;

import com.eventmanagement.eventmanager.exception.EventNotFoundException;
import com.eventmanagement.eventmanager.model.Category;
import com.eventmanagement.eventmanager.model.Event;
import com.eventmanagement.eventmanager.model.EventCategory;
import com.eventmanagement.eventmanager.model.wrapper.EventWrapper;
import com.eventmanagement.eventmanager.repo.EventCategoryRepo;
import com.eventmanagement.eventmanager.repo.EventRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private final EventRepo eventRepo;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final EventCategoryRepo eventCategoryRepo;
    @Autowired
    public EventService(EventRepo eventRepo, CategoryService categoryService, EventCategoryRepo eventCategoryRepo) {
        this.eventRepo = eventRepo;
        this.categoryService = categoryService;
        this.eventCategoryRepo = eventCategoryRepo;
    }

    @Transactional
    public Event addEvent(EventWrapper eventWrapper) {
        // First, save the event to ensure it has an ID
        Event event = eventRepo.save(eventWrapper.getEvent());

        List<Category> categories = eventWrapper.getCategoryList();
        for (Category category : categories) {
            if (categoryService.doesCategoryExist(category.getId())) {
                EventCategory eventCategory = new EventCategory();
                eventCategory.setCategory(category);
                eventCategory.setEvent(event);
                eventCategoryRepo.save(eventCategory);
            }
        }
        return event;
    }

    public List<Event> findAllEvents(){
        return eventRepo.findAll();
    }

    public Event updateEvent(Event event){
        return eventRepo.save(event);
    }

    public Event findEventById(Long id){
        return eventRepo.findEventById(id).orElseThrow(()-> new EventNotFoundException("Event "+id+" not found"));
    }

    public void deleteEvent(Long id){
        eventRepo.deleteEventById(id);
    }


}
