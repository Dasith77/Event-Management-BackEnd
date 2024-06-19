package com.eventmanagement.eventmanager.service;

import com.eventmanagement.eventmanager.exception.EventNotFoundException;
import com.eventmanagement.eventmanager.model.Category;
import com.eventmanagement.eventmanager.model.Event;
import com.eventmanagement.eventmanager.model.EventCategory;
import com.eventmanagement.eventmanager.model.wrapper.CategoryWithScore;
import com.eventmanagement.eventmanager.model.wrapper.EventWrapper;
import com.eventmanagement.eventmanager.repo.CategoryRepo;
import com.eventmanagement.eventmanager.repo.EventCategoryRepo;
import com.eventmanagement.eventmanager.repo.EventRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private final EventRepo eventRepo;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final EventCategoryRepo eventCategoryRepo;
    @Autowired
    private final CategoryRepo categoryRepo;
    @Autowired
    public EventService(EventRepo eventRepo, CategoryService categoryService, EventCategoryRepo eventCategoryRepo, CategoryRepo categoryRepo) {
        this.eventRepo = eventRepo;
        this.categoryService = categoryService;
        this.eventCategoryRepo = eventCategoryRepo;
        this.categoryRepo = categoryRepo;
    }

    @Transactional
    public Event addEvent(EventWrapper eventWrapper) {
        // First, save the event to ensure it has an ID
        Event event = eventRepo.save(eventWrapper.getEvent());

        List<CategoryWithScore> categoriesWithScores = eventWrapper.getCategoryWithScoreList();

        for (CategoryWithScore categoryWithScore : categoriesWithScores) {
            Category category = categoryRepo.findCategoryByKey(categoryWithScore.getCategoryKey());
            if (category != null) {
                EventCategory eventCategory = new EventCategory();
                eventCategory.setCategory(category);
                eventCategory.setEvent(event);
                eventCategory.setScore(categoryWithScore.getScore());
                eventCategoryRepo.save(eventCategory);
            }
        }
        return event;
    }

    public List<Optional<Event>> serachEventsByStartDate(LocalDate date){
        return eventRepo.findByStartDate(date);
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

    public List<Optional<Event>> searchEventsByStartDateAndCategory(LocalDate startDate, String categoryKey) {
        List<Optional<Event>> eventsByDate = eventRepo.findByStartDate(startDate);
        Category category = categoryRepo.findCategoryByKey(categoryKey);
        List<Long> eventIdsForCategory = eventCategoryRepo.findEventIdsByCategoryId(category.getId());
        List<Optional<Event>> eventsOnCategory = new ArrayList<>();
        for(Long eventId : eventIdsForCategory){
            Optional<Event> tempEvent = eventRepo.findEventById(eventId);
            eventsOnCategory.add(tempEvent);
        }
        List<Optional<Event>> commonEvents = eventsByDate.stream()
                .filter(eventsOnCategory::contains)
                .collect(Collectors.toList());

        return commonEvents;
    }

    public void deleteEvent(Long id){
        eventRepo.deleteEventById(id);
    }


}
