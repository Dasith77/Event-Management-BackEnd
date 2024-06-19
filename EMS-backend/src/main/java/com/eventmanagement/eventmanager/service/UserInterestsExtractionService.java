package com.eventmanagement.eventmanager.service;

import com.eventmanagement.eventmanager.model.Event;
import com.eventmanagement.eventmanager.model.EventBucket;
import com.eventmanagement.eventmanager.model.EventCategory;
import com.eventmanagement.eventmanager.model.Interest;
import com.eventmanagement.eventmanager.model.wrapper.CategoryWithScore;
import com.eventmanagement.eventmanager.model.wrapper.EventWrapper;
import com.eventmanagement.eventmanager.repo.EventBucketRepo;
import com.eventmanagement.eventmanager.repo.EventCategoryRepo;
import com.eventmanagement.eventmanager.repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInterestsExtractionService {
    @Autowired
    private final EventBucketRepo eventBucketRepo;
    @Autowired
    private final EventCategoryRepo eventCategoryRepo;

    public UserInterestsExtractionService(EventBucketRepo eventBucketRepo, EventCategoryRepo eventCategoryRepo) {
        this.eventBucketRepo = eventBucketRepo;
        this.eventCategoryRepo = eventCategoryRepo;
    }

    public Interest ExtractUserInterests(Long userId){
        List<EventBucket> eventBucketList = eventBucketRepo.findByPersonId(userId);
        List<Event> eventList = new ArrayList<>();
        List<EventWrapper> eventWrappers = new ArrayList<>();
        for(EventBucket eventBucket:eventBucketList){
            EventWrapper eventWrapper = new EventWrapper();
            List<CategoryWithScore> categoryWithScoreList = new ArrayList<>();
            eventWrapper.setEvent(eventBucket.getEvent());
            List<EventCategory> eventCategoryList = eventCategoryRepo.findByEventId(eventBucket.getEvent().getId());
            for(EventCategory eventCategory:eventCategoryList){
                CategoryWithScore categoryWithScore = new CategoryWithScore();
                categoryWithScore.setCategoryKey(eventCategory.getCategory().getCategoryKey());
                categoryWithScore.setScore(eventCategory.getScore());
                categoryWithScoreList.add(categoryWithScore);
            }
            eventWrapper.setCategoryWithScoreList(categoryWithScoreList);

            eventWrappers.add(eventWrapper);
        }
        System.out.println("================================");
        System.out.println(eventWrappers);
        return null;
    }
}
