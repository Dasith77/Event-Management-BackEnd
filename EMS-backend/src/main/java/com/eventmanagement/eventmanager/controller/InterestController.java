package com.eventmanagement.eventmanager.controller;

import com.eventmanagement.eventmanager.model.Interest;
import com.eventmanagement.eventmanager.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/interest")
public class InterestController {

    private final InterestService interestService;

    @Autowired
    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @PostMapping("/add")
    public List<Interest> addInterests(@RequestBody List<Interest> interests) {
        return interestService.addInterests(interests);
    }

    @GetMapping("/all")
    public List<Interest> findAllInterests() {
        return interestService.findAllInterests();
    }

    @GetMapping("/find/{id}")
    public Interest findInterestById(@PathVariable Long id) {
        return interestService.findInterestById(id);
    }

    @PutMapping("/update")
    public Interest updateInterest(@RequestBody Interest interest) {
        return interestService.updateInterest(interest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInterest(@PathVariable Long id) {
        interestService.deleteInterest(id);
    }
}
