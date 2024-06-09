package com.eventmanagement.eventmanager.repo;

import com.eventmanagement.eventmanager.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepo extends JpaRepository<Event, Long> {

    void deleteEventById(Long id);

    Optional<Event> findEventById(Long id);
}
