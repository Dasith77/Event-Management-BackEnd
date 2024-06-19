package com.eventmanagement.eventmanager.controller;

import com.eventmanagement.eventmanager.model.Event;
import com.eventmanagement.eventmanager.model.wrapper.EventWrapper;
import com.eventmanagement.eventmanager.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
@RestController
@RequestMapping("api/v1/event")
public class EventController {
    private final EventService eventService;


    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'NORMAL_USER')")
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> events = eventService.findAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id){
        Event event = eventService.findEventById(id);
        return new ResponseEntity<>(event,HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> addEvent(@RequestBody EventWrapper eventWrapper){
        Event newEvent = eventService.addEvent(eventWrapper);
        return new ResponseEntity<>(newEvent,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event){
        Event updatedEvent = eventService.updateEvent(event);
        return new ResponseEntity<>(updatedEvent,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id){
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/searchByDate")
    @PreAuthorize("hasAnyRole('ADMIN', 'NORMAL_USER')")
    public ResponseEntity<List<Optional<Event>>> searchEventsByStartDate(@RequestParam("startDate") LocalDate startDate) {
        List<Optional<Event>> events = eventService.serachEventsByStartDate(startDate);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'NORMAL_USER')")
    public ResponseEntity<List<Optional<Event>>> searchEventsByStartDateAndCategory(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "categoryKey", required = false) String categoryKey) {
        List<Optional<Event>> events = eventService.searchEventsByStartDateAndCategory(startDate, categoryKey);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }


}
