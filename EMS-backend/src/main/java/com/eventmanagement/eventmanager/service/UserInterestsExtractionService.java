package com.eventmanagement.eventmanager.service;

import com.eventmanagement.eventmanager.model.*;
import com.eventmanagement.eventmanager.model.wrapper.CategoryWithScore;
import com.eventmanagement.eventmanager.model.wrapper.EventWrapper;
import com.eventmanagement.eventmanager.repo.EventBucketRepo;
import com.eventmanagement.eventmanager.repo.EventCategoryRepo;
import com.eventmanagement.eventmanager.repo.EventRepo;
import com.eventmanagement.eventmanager.repo.InterestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserInterestsExtractionService {
    @Autowired
    private final EventBucketRepo eventBucketRepo;
    @Autowired
    private final EventCategoryRepo eventCategoryRepo;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final InterestRepo interestRepo;

    public UserInterestsExtractionService(EventBucketRepo eventBucketRepo, EventCategoryRepo eventCategoryRepo, CategoryService categoryService, InterestRepo interestRepo) {
        this.eventBucketRepo = eventBucketRepo;
        this.eventCategoryRepo = eventCategoryRepo;
        this.categoryService = categoryService;
        this.interestRepo = interestRepo;
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

        List<List<Integer>> categoryInterestsMatrix = getCategoryInterestsMatrix();

        Interest finalInterest =  extractInterest(eventCategoryMatrix,categoryInterestsMatrix);




        return finalInterest;
    }

    private Interest extractInterest(List<List<Integer>> eventCategoryMatrix, List<List<Integer>> categoryInterestsMatrix) {
        int rows1 = eventCategoryMatrix.size();
        int cols1 = eventCategoryMatrix.get(0).size();
        int rows2 = categoryInterestsMatrix.size();
        int cols2 = categoryInterestsMatrix.get(0).size();

        // Check if matrix multiplication is possible
        if (cols1 != rows2) {
            throw new IllegalArgumentException("Matrix multiplication is not possible. Column count of eventCategoryMatrix must equal row count of categoryInterestsMatrix.");
        }

        // Initialize the result matrix
        List<List<Integer>> resultMatrix = new ArrayList<>(rows1);
        for (int i = 0; i < rows1; i++) {
            List<Integer> row = new ArrayList<>(cols2);
            for (int j = 0; j < cols2; j++) {
                row.add(0);
            }
            resultMatrix.add(row);
        }

        // Perform matrix multiplication
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                int sum = 0;
                for (int k = 0; k < cols1; k++) {
                    sum += eventCategoryMatrix.get(i).get(k) * categoryInterestsMatrix.get(k).get(j);
                }
                resultMatrix.get(i).set(j, sum);
            }
        }

        // Calculate the column-wise sum
        int[] columnSums = new int[cols2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                columnSums[j] += resultMatrix.get(i).get(j);
            }
        }

        // Find the maximum column sum and its index
        int maxSum = Integer.MIN_VALUE;
        long maxIndex = -1;
        for (int i = 0; i < columnSums.length; i++) {
            if (columnSums[i] > maxSum) {
                maxSum = columnSums[i];
                maxIndex = i;
            }
        }

        Interest finalInterest = interestRepo.findInterestById(maxIndex+1);

        return finalInterest;


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
        System.out.println("==========================================================");
        System.out.println(eventCategoryMatrix);
        System.out.println("==========================================================");
        return eventCategoryMatrix;
    }

    public List<List<Integer>> getCategoryInterestsMatrix(){

        List<List<Integer>> matrix = new ArrayList<>();
        String filePath = "Category-Interests_matrix.csv"; // Path within the resources directory

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (is == null) {
                throw new FileNotFoundException("Resource not found: " + filePath);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    List<Integer> row = new ArrayList<>();
                    for (String value : values) {
                        row.add(Integer.parseInt(value));
                    }
                    matrix.add(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matrix;
    }



}
