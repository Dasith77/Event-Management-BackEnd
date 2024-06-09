package com.eventmanagement.eventmanager.service;

import com.eventmanagement.eventmanager.model.Category;
import com.eventmanagement.eventmanager.model.Interest;
import com.eventmanagement.eventmanager.repo.InterestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestService {
    private final InterestRepo interestRepo;

    @Autowired
    public InterestService(InterestRepo interestRepo) {
        this.interestRepo = interestRepo;
    }

    public List<Interest> addInterests(List<Interest> interests) {
        return interestRepo.saveAll(interests);
    }
    public List<Interest> findAllInterests(){
        return interestRepo.findAll();
    }
    public Interest findInterestById(Long id){
        return interestRepo.findInterestById(id);
    }


    public Interest updateInterest(Interest interest){
        return interestRepo.save(interest);
    }

    public void deleteInterest(Long id){
        interestRepo.deleteInterestById(id);
    }

}
