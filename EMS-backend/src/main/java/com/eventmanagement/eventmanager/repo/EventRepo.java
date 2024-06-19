package com.eventmanagement.eventmanager.repo;

import com.eventmanagement.eventmanager.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepo extends JpaRepository<Event, Long> {

    void deleteEventById(Long id);

    Optional<Event> findEventById(Long id);

    @Query("SELECT e FROM Event e WHERE e.startDate = :startDate")
    List<Optional<Event>> findByStartDate(@Param("startDate") LocalDate startDate);

    @Query("SELECT e FROM Event e JOIN EventCategory ec ON e.id = ec.event.id JOIN Category c ON ec.category.id = c.id WHERE (:startDate IS NULL OR e.startDate = :startDate) AND (:categoryKey IS NULL OR c.categoryKey = :categoryKey)")
    List<Event> findByStartDateAndCategory(@Param("startDate") LocalDate startDate, @Param("categoryKey") String categoryKey);
}
