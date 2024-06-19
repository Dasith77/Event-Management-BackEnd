package com.eventmanagement.eventmanager.repo;

import com.eventmanagement.eventmanager.model.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventCategoryRepo extends JpaRepository<EventCategory,Long> {

    @Query("SELECT ec.event.id FROM EventCategory ec WHERE ec.category.id = :categoryId")
    List<Long> findEventIdsByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT ec FROM EventCategory ec WHERE ec.event.id = :eventId")
    List<EventCategory> findByEventId(@Param("eventId") Long eventId);

}
