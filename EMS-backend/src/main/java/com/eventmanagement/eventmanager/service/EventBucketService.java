package com.eventmanagement.eventmanager.service;

import com.eventmanagement.eventmanager.model.Event;
import com.eventmanagement.eventmanager.model.EventBucket;
import com.eventmanagement.eventmanager.model.Person;
import com.eventmanagement.eventmanager.model.dto.EventDTO;
import com.eventmanagement.eventmanager.repo.EventBucketRepo;
import com.eventmanagement.eventmanager.repo.EventRepo;
import com.eventmanagement.eventmanager.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventBucketService {

    @Autowired
    private final EventBucketRepo eventBucketRepo;
    @Autowired
    private final EventRepo eventRepo;
    @Autowired
    private final PersonRepo personRepo;


    public EventBucketService(EventBucketRepo eventBucketRepo, EventRepo eventRepo, PersonRepo personRepo) {
        this.eventBucketRepo = eventBucketRepo;
        this.eventRepo = eventRepo;
        this.personRepo = personRepo;
    }

    public EventBucket saveEventBucket(EventDTO eventDTO) {
        EventBucket eventBucket = new EventBucket();

        Event event = eventRepo.findById(eventDTO.getId()).orElseThrow(() -> new RuntimeException("Event not found"));
        Person person = personRepo.findById(eventDTO.getUserId()).orElseThrow(() -> new RuntimeException("Person not found"));

        eventBucket.setEvent(event);
        eventBucket.setUser(person);

        return eventBucketRepo.save(eventBucket);
    }
}
