package com.eventmanagement.eventmanager.service;

import com.eventmanagement.eventmanager.model.*;
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
    @Autowired
    private final CategoryService categoryService;

    public UserInterestsExtractionService(EventBucketRepo eventBucketRepo, EventCategoryRepo eventCategoryRepo, CategoryService categoryService) {
        this.eventBucketRepo = eventBucketRepo;
        this.eventCategoryRepo = eventCategoryRepo;
        this.categoryService = categoryService;
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
        List<List<Integer>> eventCategoryMatrix = getEventCategoryMatrix(eventWrappers);








        System.out.println("================================");
        System.out.println(eventWrappers);
        return null;
    }

    public List<List<Integer>> getEventCategoryMatrix(List<EventWrapper> eventWrappers){
        List<List<Integer>> eventCategoryMatrix = new ArrayList<>();
        List<Category> allCategories = categoryService.findAllCategories();
        Integer numOfCategories = allCategories.size();
        for(EventWrapper eventWrapper:eventWrappers){
            List<CategoryWithScore> categoryWithScoreList = eventWrapper.getCategoryWithScoreList();
            List<Integer> tempScoreList = new ArrayList<>();
            for (Category category : allCategories) {
                String categoryKey = category.getCategoryKey();
                boolean found = false;
                for (CategoryWithScore categoryWithScore : categoryWithScoreList) {
                    if (categoryWithScore.getCategoryKey().equals(categoryKey)) {
                        tempScoreList.add(categoryWithScore.getScore());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    tempScoreList.add(0);
                }
            }
            eventCategoryMatrix.add(tempScoreList);
        }
        return eventCategoryMatrix;
    }




}
