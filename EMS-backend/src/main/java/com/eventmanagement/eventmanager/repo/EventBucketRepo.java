package com.eventmanagement.eventmanager.repo;

import com.eventmanagement.eventmanager.model.EventBucket;
import com.eventmanagement.eventmanager.model.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventBucketRepo extends JpaRepository<EventBucket,Long> {
    List<EventBucket> findByPersonId(Long personId);
}
