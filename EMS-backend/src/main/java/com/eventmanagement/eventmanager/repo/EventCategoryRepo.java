package com.eventmanagement.eventmanager.repo;

import com.eventmanagement.eventmanager.model.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCategoryRepo extends JpaRepository<EventCategory,Long> {


}
