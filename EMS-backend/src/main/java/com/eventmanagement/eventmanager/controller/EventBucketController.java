package com.eventmanagement.eventmanager.controller;

import com.eventmanagement.eventmanager.model.EventBucket;
import com.eventmanagement.eventmanager.model.dto.EventDTO;
import com.eventmanagement.eventmanager.service.EventBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/event-buckets")
public class EventBucketController {

    private final EventBucketService eventBucketService;

    @Autowired
    public EventBucketController(EventBucketService eventBucketService) {
        this.eventBucketService = eventBucketService;
    }

    @PostMapping("/save")
    public ResponseEntity<EventBucket> createEventBucket(@RequestBody EventDTO eventDTO) {
        EventBucket createdEventBucket = eventBucketService.saveEventBucket(eventDTO);
        return ResponseEntity.ok(createdEventBucket);
    }
}
