package com.eventmanagement.eventmanager.controller;

import com.eventmanagement.eventmanager.model.Interest;
import com.eventmanagement.eventmanager.service.UserInterestsExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user-interests")
public class UserInterestsExtractionController {

    @Autowired
    private final UserInterestsExtractionService userInterestsExtractionService;

    public UserInterestsExtractionController(UserInterestsExtractionService userInterestsExtractionService) {
        this.userInterestsExtractionService = userInterestsExtractionService;
    }

    @GetMapping
    public Interest getUserInterests(@RequestParam Long userId) {
        return userInterestsExtractionService.ExtractUserInterests(userId);
    }
}
